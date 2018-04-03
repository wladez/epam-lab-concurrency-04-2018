package lesson_30_03_2018;

public class Example2 {

    private static RuntimeException ex = new RuntimeException();

    public Example2() {
    }

    public static void main(String[] args) {
        method(0);
    }

    private static void method(int value) {
        try {
            method2(value);
        } catch (RuntimeException ex) {
            System.out.println("Here");
            throw ex;
        }
    }

    private static void method2(int value) {
        throw ex;
    }
}
