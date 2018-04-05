package lesson_04_04_2018;

@SuppressWarnings("all")
public class Example1 {

    private static volatile Integer value = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread inc = new Thread(() -> {
            for (int i = 0; i < 1000; ++i) {
                synchronized (value) {
                    ++value;
                }
            }
        });

        Thread dec = new Thread(() -> {
            for (int i = 0; i < 1000; ++i) {
                synchronized (value) {
                    --value;
                }
            }
        });

        inc.start();
        dec.start();

        inc.join();
        dec.join();

        System.out.println(value);
    }

}
