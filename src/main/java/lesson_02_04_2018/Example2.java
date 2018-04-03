package lesson_02_04_2018;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Example2 {

    public static void main(String[] args) throws InterruptedException {
        Runnable counter = () -> {
            long result = 0;
            while (!Thread.interrupted()) {
                ++result;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Inside catch block: " + Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().isInterrupted());
            System.out.println(result);
        };

        Thread counter1 = new Thread(counter, "Counter-1");
        counter1.start();

        TimeUnit.SECONDS.sleep(1);
        counter1.interrupt();
    }
}
