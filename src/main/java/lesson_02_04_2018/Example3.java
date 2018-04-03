package lesson_02_04_2018;

import java.util.concurrent.TimeUnit;

public class Example3 {

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        Runnable counter = () -> {
            long result = 0;
            while (!Thread.interrupted()) {
                ++result;
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + ": " + result);
        };

        int countCores = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[countCores + 2];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(counter, "Counter-" + i);
            threads[i].setPriority(i < countCores ? Thread.MAX_PRIORITY : Thread.MIN_PRIORITY);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        TimeUnit.SECONDS.sleep(1);
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
