package lesson_30_03_2018;

public class Example1 {

    public static void main(String[] args) {
        method(0);
    }

    private static void method(int i) {
        Integer val1 = 10;
        Integer val2 = 15;
        System.out.println(val1);
        System.out.println(val2);
        System.out.println(i);
        method(++i);
    }
}
