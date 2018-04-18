package lesson_16_04_2018;

import java.util.concurrent.TimeUnit;

public class Example2 {

    private static volatile long value = 10;

    public static void main(String[] args) throws InterruptedException {
        // Compare
        // And
        // Swap / Set

        new Thread(() -> {
            ++value;
        }).start();

        new Thread(() -> {
            --value;
        }).start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println(value);


        //                   10
        //      T1                             T2
        // CAS(10, 11) => false         CAS(10, 9) => true
        //                    9
        // CAS(9, 10) => true

    }
}
