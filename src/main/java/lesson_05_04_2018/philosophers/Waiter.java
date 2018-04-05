package lesson_05_04_2018.philosophers;

import java.util.concurrent.TimeUnit;

public class Waiter {

    private static final Table table = new Table();
    private static volatile boolean started = false;

    public static void main(String[] args) throws InterruptedException {
        Thread waiter = Thread.currentThread();
        waiter.setName("Waiter");

        System.out.println("Creating philisophers");
        Philosopher mo = createPhilosopher("Mo");
        Philosopher lao = createPhilosopher("Lao");
        Philosopher kun = createPhilosopher("Kun");
        mo.inviteTo(table);
        lao.inviteTo(table);
        kun.inviteTo(table);

        synchronized (table) {
            started = true;
            table.notifyAll();
            TimeUnit.SECONDS.sleep(10);
            new Thread(() -> {
                synchronized (table) {
                    System.out.println("3213");
                }
            }).start();
            System.out.println("123");
        }


        while (true) {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Do u need something?");
        }
    }

    private static Philosopher createPhilosopher(String name) {
        Philosopher philosopher = new Philosopher(name);
        philosopher.setBehaviour(() -> {
            try {
                synchronized (table) {
                    while (!started) {
                        table.wait();
                    }
                    System.out.println("after wait");
                }

                synchronized (table.getStickWithLowestIndex(philosopher)) {
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (table.getStickWithHighestIndex(philosopher)) {
                        System.out.println("Eating " + Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(5);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return philosopher;
    }
}
