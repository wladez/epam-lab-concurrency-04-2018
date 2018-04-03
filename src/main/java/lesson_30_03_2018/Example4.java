package lesson_30_03_2018;

import java.util.concurrent.TimeUnit;

public class Example4 {

    private int val = 42;

    public static void main(String[] args) throws InterruptedException {
        Runnable counter = () -> {
            int count = 0;
            while (true) {
                ++count;
                System.out.println(count);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(counter, "Counter").start();
        new Counter().start();

        TimeUnit.SECONDS.sleep(10);
        System.out.println(Thread.currentThread().getId());
        System.out.println("Main end");
    }

    interface A {
         double val();
    }

    public static class Counter extends Thread {

        public Counter() {
            super("Counter");
        }

        @Override
        public void run() {
            int count = 0;
            while (true) {
                ++count;
                System.out.println(count);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
