package lesson_05_04_2018.storages;

import java.util.concurrent.TimeUnit;

public class Writer extends Thread {
    private Storage storage;
    private final Object monitor;

    Writer(Storage storage, Object monitor) {
        super("Writer");
        this.storage = storage;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        int val = 1;
        while (true) {
            try {
                synchronized (monitor) {
                    monitor.wait();
                    storage.setValue("ABC " + val);
                }
                System.out.println("Set new value in Thread " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
                val++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
