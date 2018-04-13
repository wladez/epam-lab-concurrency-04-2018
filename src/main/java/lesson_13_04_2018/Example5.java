package lesson_13_04_2018;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Example5 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        Phaser phaser = new Phaser(2);
        System.out.println(phaser.getPhase());
        System.out.println(phaser.getRegisteredParties());
        System.out.println(phaser.getArrivedParties());

        service.execute(() -> {
            System.out.println("Before arrive & wait");
            phaser.arriveAndAwaitAdvance();
            System.out.println("Arrived");

            phaser.arriveAndDeregister();
            System.out.println(phaser.getRegisteredParties());
            System.out.println(phaser.getArrivedParties());
        });


        TimeUnit.SECONDS.sleep(2);
        System.out.println(phaser.getRegisteredParties());
        System.out.println(phaser.getArrivedParties());
        phaser.arriveAndAwaitAdvance();

        System.out.println(phaser.getPhase());


        TimeUnit.SECONDS.sleep(2);

        System.out.println(phaser.getRegisteredParties());
        System.out.println(phaser.getArrivedParties());
        phaser.arriveAndAwaitAdvance();

        System.out.println(phaser.getPhase());
        phaser.arriveAndDeregister();




        service.shutdown();
    }
}
