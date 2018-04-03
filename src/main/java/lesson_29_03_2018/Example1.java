package lesson_29_03_2018;

import java.util.concurrent.TimeUnit;

public class Example1 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");

        TimeUnit.DAYS.sleep(1);

        long millisIn10Days = TimeUnit.DAYS.toMillis(10);
    }
}
