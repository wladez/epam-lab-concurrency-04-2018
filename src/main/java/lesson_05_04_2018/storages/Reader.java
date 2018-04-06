package lesson_05_04_2018.storages;

import java.util.concurrent.TimeUnit;

public class Reader extends Thread {
    private Storage storage;
    private final Object monitor;

    Reader(Storage storage, Object monitor, String name) {
        super(name);
        this.storage = storage;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (monitor) {
                    System.out.println("Thread " + Thread.currentThread()
                                                         .getName() + " read from Storage value " + storage.getValue());
                    monitor.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
