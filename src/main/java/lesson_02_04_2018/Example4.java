package lesson_02_04_2018;

import java.util.concurrent.TimeUnit;

public class Example4 {

    public static void main(String[] args) throws InterruptedException {
        String string = "hello";

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                synchronized (string) {
                    System.out.println("From inner thread" + string);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        synchronized (string) {
            TimeUnit.SECONDS.sleep(5);
            System.out.println(string);
        }


    }
}
