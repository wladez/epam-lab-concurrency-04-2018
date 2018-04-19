package lesson_18_04_2018.sum;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 1000;
    private final int[] data;
    private final int fromInclusive;
    private final int toExclusive;

    public SumTask(int[] data) {
        this(data, 0, data.length);
    }

    private SumTask(int[] data, int fromInclusive, int toExclusive) {
        this.data = data;
        this.fromInclusive = fromInclusive;
        this.toExclusive = toExclusive;
    }

    @Override
    protected Integer compute() {
        if (toExclusive - fromInclusive < THRESHOLD) {
            return Arrays.stream(data)
                         .skip(fromInclusive)
                         .limit(toExclusive - fromInclusive)
                         .sum();
        } else {
            System.out.println("Split");
            int mid = (fromInclusive + toExclusive) / 2;
            SumTask left = new SumTask(data, fromInclusive, mid);
            SumTask right = new SumTask(data, mid, toExclusive);

            left.fork();
            right.fork();

            return right.join() + left.join();
        }
    }
}
