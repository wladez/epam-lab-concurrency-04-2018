package lesson_11_04_2018;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Example1 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        Lock lock = new ReentrantLock();


        service.submit(() -> {


            try {
                TimeUnit.SECONDS.sleep(1);

                System.out.println("Trying to lock");
                lock.lock();
                System.out.println("Lock is mine!");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });




    }
}
