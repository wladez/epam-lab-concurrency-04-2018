package lesson_13_04_2018;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Example3 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        Runnable action = () -> {
            System.out.println(Thread.currentThread());
            System.out.println("Barrier down");
            throw new RuntimeException("Exception in barrier action");
        };
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        Thread main = Thread.currentThread();

        service.execute(() -> {
            main.interrupt();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(cyclicBarrier.isBroken());
        });

        try {
            System.out.println("Main before barrier");
            cyclicBarrier.await();
            System.out.println("Main end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        TimeUnit.SECONDS.sleep(3);
        System.out.println(cyclicBarrier.isBroken());
    }
}
