package lesson_03_04_2018;

public class Example4 {

    private static volatile int value = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread inc = new Thread(() -> {
            for (int i = 0; i < 1_000_000_000; ++i) {
                ++value;
            }
        });

        Thread dec = new Thread(() -> {
            for (int i = 0; i < 1_000_000_000; ++i) {
                --value;
            }
        });

        inc.start();
        dec.start();

        inc.join();
        dec.join();

        System.out.println(value);

    }

    // ++value
    // load value
    // inc value
    // store value
    //     T1            value               T2
    //                     0
    //     0  <-                         ->  0
    //     1                                 -1
    //                     1
    //                    -1

    //                     0
    //     0 <-
    //     1
    //                  -> 1
    //                                   -> 1
    //                                      0
    //                     0 <-

}
