package lesson_18_04_2018;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

public class Example1 {

    public static void main(String[] args) {
        int[] data = ThreadLocalRandom.current()
                                      .ints(40, 0, 100)
                                      .toArray();

        System.out.println("Before: " + Arrays.toString(data));

        new ForkJoinPool().invoke(new ForkJoinQuicksortAction(data, 0, data.length - 1));
        System.out.println("After: " + Arrays.toString(data));
    }

    private static class ForkJoinQuicksortAction extends RecursiveAction {
        private static final int SEQUENTIAL_THRESHOLD = 10;

        private int[] data;
        private int fromInclusive;
        private int toInclusive;

        private ForkJoinQuicksortAction(int[] data, int fromInclusive, int toInclusive) {
            this.fromInclusive = fromInclusive;
            this.toInclusive = toInclusive;
            this.data = data;
        }

        @Override
        protected void compute() {
            if (toInclusive - fromInclusive < SEQUENTIAL_THRESHOLD) {
                System.out.println("Sequential part");
                Arrays.sort(data, fromInclusive, toInclusive + 1);
            } else {
                System.out.println("Split");
                int pivot = partition(data, fromInclusive, toInclusive);
                ForkJoinQuicksortAction left = new ForkJoinQuicksortAction(data, fromInclusive, pivot);
                ForkJoinQuicksortAction right = new ForkJoinQuicksortAction(data, pivot + 1, toInclusive);

                ForkJoinTask.invokeAll(left, right);
            }
        }

        private int partition(int[] array, int fromInclusive, int toExclusive) {
            int pivot = array[fromInclusive];
            int i = fromInclusive - 1;
            int j  = toExclusive + 1;
            while (true){
                do {
                    i++;
                }
                while (array[i] < pivot);

                do {
                    j--;
                }
                while (array[j] > pivot);

                if (i >= j) {
                    return j;
                }

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
    }
}
