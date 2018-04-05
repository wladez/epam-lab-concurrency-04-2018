package lesson_05_04_2018;

import java.util.concurrent.TimeUnit;

public class Example1 {

    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();


        new Thread(() -> {
            synchronized (a) {
                try {
                    System.out.println("1 - Got it: a");
                    TimeUnit.SECONDS.sleep(3);
                    synchronized (b) {
                        System.out.println("1 - Got it: b");
                        TimeUnit.SECONDS.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (b) {
                try {
                    System.out.println("2 - Got it: b");
                    TimeUnit.SECONDS.sleep(3);
                    synchronized (a) {
                        System.out.println("2 - Got it: a");
                        TimeUnit.SECONDS.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
