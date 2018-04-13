package lesson_13_04_2018;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Example2 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        CountDownLatch countDownLatch = new CountDownLatch(2);

        // main: "race prepare"
        // T1: start-1
        // T2: start-2
        // T3: start-3
        // main: ready
        // main: steady
        // main: go
        // main: "race starts" <-
        // T2: end-2
        // T3: end-3
        // T1: end-1
        // main: "race ends"

        service.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                countDownLatch.countDown();
                System.out.println("1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                countDownLatch.countDown();
                System.out.println("2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            countDownLatch.await();
            System.out.println("3");
            countDownLatch.await();
            System.out.println("4");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
