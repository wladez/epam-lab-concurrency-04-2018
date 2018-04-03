package lesson_03_04_2018;

import java.util.concurrent.TimeUnit;

public class Example5 {


    private static class Counter {

        private final Object monitor = new Object();
        private volatile int value;

        void inc() {
            synchronized (monitor) {
                ++value;
            }
            System.out.println(value);
        }

        synchronized void dec() {
            --value;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread inc = new Thread(() -> {
            for (int i = 0; i < 1_000_000_000; ++i) {
                counter.inc();
            }
        });

        Thread dec = new Thread(() -> {
            for (int i = 0; i < 1_000_000_000; ++i) {
                counter.dec();
            }
        });


        synchronized (counter) {
            TimeUnit.SECONDS.sleep(100);
        }

        inc.start();
        dec.start();

        inc.join();
        dec.join();

        System.out.println(counter.getValue());
    }}
