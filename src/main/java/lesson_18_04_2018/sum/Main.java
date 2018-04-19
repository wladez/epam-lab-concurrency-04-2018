package lesson_18_04_2018.sum;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        int[] ints = IntStream.generate(() -> 1).limit(100_000).toArray();

        SumTask sumTask = new SumTask(ints);

        Integer invoke = ForkJoinPool.commonPool().invoke(sumTask);
        System.out.println(invoke);
    }
}
