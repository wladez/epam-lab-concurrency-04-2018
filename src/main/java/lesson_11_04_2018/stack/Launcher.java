package lesson_11_04_2018.stack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Launcher {
    public static void main(String[] args) {
        BlockingStack<Integer> stack = new BlockingStack<>(5);
        ExecutorService service = Executors.newFixedThreadPool(15);

        service.submit(() -> {
            for (int i = 0; i < 7; i++) {
                try {
                    stack.push(i);
                    System.out.println("Put in stack " + i);
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        service.submit(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("Get from stack " + stack.pop());
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        service.shutdown();
    }
}
