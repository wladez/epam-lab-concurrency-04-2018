package lesson_12_04_2018;

import lombok.Value;

import java.util.concurrent.*;

public class Example2 {

    @Value
    private static class Box {
        String destination;
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        BlockingQueue<Box> desk = new LinkedBlockingQueue<>(5);
        int numBoxes = 10;

        Runnable operator = () -> {
            try {
                for (int i = 0; i < numBoxes; ++i) {
                    System.out.println("Prepare box number - " + i);
                    TimeUnit.SECONDS.sleep(1);
                    Box box = new Box(String.valueOf(i));

                    System.out.println("Trying put " + i + " on the desk");
                    desk.put(box);
                    System.out.println("Sucessfully put " + i + " on the desk");
                }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        };

        Runnable courier = () -> {
            try {
                for (int i = 0; i < numBoxes; ++i) {
                    System.err.println("Trying take box from the desk");
                    Box box = desk.take();
                    System.err.println("Took the " + box + ", going to deliver it");
                    TimeUnit.SECONDS.sleep(5);
                }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        };

        service.submit(operator);
        service.submit(courier);
        service.shutdown();
    }
}
