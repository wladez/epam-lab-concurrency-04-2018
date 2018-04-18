package lesson_16_04_2018;

import java.util.concurrent.Phaser;

public class Example1 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(2);
        phaser.arriveAndDeregister();



        System.out.println(phaser.getPhase());
        System.out.println(phaser.getArrivedParties());
        System.out.println(phaser.getRegisteredParties());
    }
}
