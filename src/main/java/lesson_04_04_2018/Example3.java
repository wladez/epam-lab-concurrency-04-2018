package lesson_04_04_2018;

@SuppressWarnings("all")
public class Example3 {

    private static volatile int value = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread inc = new Thread(() -> {
            for (int i = 0; i < 1000; ++i) {
                incValue();
            }
        });

        Thread dec1 = new Thread(() -> {
            for (int i = 0; i < 1000; ++i) {
                decValue();
            }
        });

        Thread dec2 = new Thread(() -> {
            for (int i = 0; i < 1000; ++i) {
                decValue();
            }
        });

        Thread dec3 = new Thread(() -> {
            for (int i = 0; i < 1000; ++i) {
                decValue();
            }
        });

        inc.start();
        dec1.start();
        dec2.start();
        dec3.start();

        inc.join();
        dec1.join();
        dec2.join();
        dec3.join();

        System.out.println(value);
    }

    // <- inc +
    private static synchronized int decValue() {
        return --value;
    }

    // <- dec1 ~
    // <- dec2 ~
    // <- dec3
    private static int incValue() {
        synchronized (Example3.class) {
            return ++value;
        }
    }

}
