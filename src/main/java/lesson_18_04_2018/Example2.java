package lesson_18_04_2018;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class Example2 {

    public static void main(String[] args) {
        int[] data = IntStream.rangeClosed(0, 1_000_000)
                              .map(i -> 1)
                              .toArray();


//        Integer result = new ForkJoinPool().invoke(new ForkJoinSumArrayTask(data));
//        System.out.println("Result: " + result);
//        new ForkJoinSumArrayTask(data).invoke();
        ForkJoinPool.commonPool().invoke(new ForkJoinSumArrayTask(data));
    }

    private static class ForkJoinSumArrayTask extends RecursiveTask<Integer> {

        private static final int SEQUENTIAL_THRESHOLD = 1_000_000_000;

        private int[] data;
        private int fromInclusive;
        private int toExclusive;

        public ForkJoinSumArrayTask(int[] data) {
            this(data, 0, data.length);
        }

        public ForkJoinSumArrayTask(int[] data, int fromInclusive, int toExclusive) {
            this.fromInclusive = fromInclusive;
            this.toExclusive = toExclusive;
            this.data = data;
        }

        @Override
        protected Integer compute() {
            if (toExclusive - fromInclusive < SEQUENTIAL_THRESHOLD) {
                return Arrays.stream(data)
                             .skip(fromInclusive)
                             .limit(toExclusive - fromInclusive)
                             .sum();
            }
            int mid = (fromInclusive + toExclusive) / 2;

            ForkJoinSumArrayTask left = new ForkJoinSumArrayTask(data, fromInclusive, mid);
            ForkJoinSumArrayTask right = new ForkJoinSumArrayTask(data, mid, toExclusive);

            ForkJoinTask.invokeAll(left, right);

            return right.join() + left.join();
        }
    }


}
