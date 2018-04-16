package lesson_13_04_2018.tasks;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Parking {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(5, true);

        Runnable task = () -> {
            try {
                semaphore.acquire();

                System.out.println("Car № " + Thread.currentThread().getName() + " rode into parking");
                Random random = new Random();
                TimeUnit.SECONDS.sleep(2 + random.nextInt(7-2));

                semaphore.release();
                System.out.println("Car № " + Thread.currentThread().getName() + " leave parking. Available places: " + semaphore.availablePermits());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        service.submit(task);
        service.submit(task);
        service.submit(task);
        service.submit(task);
        service.submit(task);
        service.submit(task);
        service.submit(task);
        service.submit(task);
        service.submit(task);

        service.shutdown();
    }
}
