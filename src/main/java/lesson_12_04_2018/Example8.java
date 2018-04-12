package lesson_12_04_2018;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Example8 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        Semaphore semaphore = new Semaphore(0);

        service.submit(() -> {
            try {
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Done 1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.submit(() -> {
            try {
                semaphore.acquire(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release(4);
            System.out.println("Done 2");
        });

    }
}
