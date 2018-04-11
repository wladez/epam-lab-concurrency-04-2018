package lesson_11_04_2018;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Example4 {


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        // [_, _, _, _, _]
        //  |
        queue.put(1);

        // [6, 7, 3, 4, 5]
        //     |
    }
}
