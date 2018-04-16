package lesson_13_04_2018.tasks;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Race {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(3);

        Runnable task = () -> {
            try {
                latch.await();
                Random random = new Random();
                TimeUnit.SECONDS.sleep(2 - random.nextInt(8-2));
                System.out.println(Thread.currentThread().getName() + " finished!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        service.execute(task);
        service.execute(task);
        service.execute(task);
        service.execute(task);
        service.execute(task);

        try {
            System.out.println("READY!");
            latch.countDown();
            TimeUnit.SECONDS.sleep(1);

            System.out.println("STEADY!");
            latch.countDown();
            TimeUnit.SECONDS.sleep(1);

            System.out.println("GO!");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.shutdown();
    }
}
