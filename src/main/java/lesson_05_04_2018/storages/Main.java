package lesson_05_04_2018.storages;

public class Main {
    public static void main(String[] args) {
        Object monitor = new Object();
        Storage storage = new Storage();
        Writer writer = new Writer(storage, monitor);
        Reader reader1 = new Reader(storage, monitor, "Reader1");
        Reader reader2 = new Reader(storage, monitor, "Reader2");
        Reader reader3 = new Reader(storage, monitor, "Reader3");

        writer.setPriority(Thread.MAX_PRIORITY);

        writer.start();
        reader1.start();
        reader2.start();
        reader3.start();
        System.out.println("Main end!");
    }
}
