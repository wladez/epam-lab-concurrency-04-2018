package lesson_04_04_2018;

import lombok.Synchronized;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class Example6 {

    private static class Counter {

        protected volatile int value;

        synchronized void inc() {
            ++value;
        }

        synchronized void dec() {
            --value;
        }

        public int getValue() {
            return value;
        }
    }

    private static class ChildCounter extends Counter {

        @Override
        void inc() {
            value += 2;
        }
    }

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        Counter counter = new ChildCounter();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                for (int i = 0; i < 100; ++i) {
                    counter.inc();
                }
                System.out.println(counter.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Field $lockField = Counter.class.getDeclaredField("$lock");
        $lockField.setAccessible(true);
        Object lock = $lockField.get(counter);
        synchronized (lock) {
            TimeUnit.SECONDS.sleep(10);
        }
        System.out.println("Main end");

    }
}
