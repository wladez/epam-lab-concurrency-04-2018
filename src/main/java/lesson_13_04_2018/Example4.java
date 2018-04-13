package lesson_13_04_2018;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Example4 {

    class Buffer {

        private final int[] arr = new int[1_000_000];
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        Exchanger<String> exchanger = new Exchanger<>();

        service.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                String result = exchanger.exchange(null);
                System.out.println(Thread.currentThread() + ": " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        String result = null;
        try {
            result = exchanger.exchange("123");
            System.out.println(Thread.currentThread() + ": " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.shutdown();
    }
}
