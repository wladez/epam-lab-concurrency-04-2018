package lesson_03_04_2018;

import java.util.concurrent.TimeUnit;

public class Example2 {

    public static void main(String[] args) throws InterruptedException {
        Thread counter = new Thread(() -> {
            try {
                int val = 0;
                for (int j = 0; j < 1_000_000_000; ++j) {
                    for (int i = 0; i < 1_000_000_000; ++i) {
                        ++val;
                    }
                    System.out.println(val);
                }
                System.out.println("Value: " + val);
            } finally {
                System.out.println("Finally");
            }
        });
        counter.setDaemon(true);
        counter.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(counter.isAlive());
        counter.setDaemon(true);
    }
}
