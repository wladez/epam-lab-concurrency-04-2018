package lesson_13_04_2018;

import java.util.concurrent.*;

public class Example1 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        Semaphore semaphore = new Semaphore(Integer.MAX_VALUE);

//        service.submit(() -> {
//            try {
//                semaphore.acquire();
//                TimeUnit.SECONDS.sleep(5);
//                System.out.println("Done 1");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

        Future<?> submit = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Before: " + semaphore.availablePermits());
            semaphore.release();
            System.out.println("After: " + semaphore.availablePermits());
            System.out.println("Done 2");
        });

        try {
            submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        service.shutdown();
    }
}
