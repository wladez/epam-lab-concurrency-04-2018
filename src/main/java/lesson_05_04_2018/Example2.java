package lesson_05_04_2018;

import java.util.concurrent.TimeUnit;

public class Example2 {


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main start");
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Before sleep");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("After sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        synchronized (thread) {
            thread.wait();
        }
        System.out.println("Main end");
    }
}
