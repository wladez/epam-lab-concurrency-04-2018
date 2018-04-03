package lesson_30_03_2018;

public class Example3 {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t);
                System.out.println(e);
            }
        });
        method();
    }

    private static void method() {
        throw new RuntimeException("Some ex");
    }
}
