package lesson_16_04_2018;

import java.util.concurrent.atomic.*;

public class Example3 {

    // T1: 0 -> 1  ~
    // T2: 0 -> 1
    // T3: 0 -> -1  1 -> 0

    // ABA - problem
    private static class Counter {

        private final AtomicInteger counter = new AtomicInteger();

        void inc() {
            int actualValue;
            do {
                actualValue = counter.get();
            } while (!counter.compareAndSet(actualValue, actualValue + 1));
        }

        void dec() {
            int actualValue;
            do {
                actualValue = counter.get();
            } while (!counter.compareAndSet(actualValue, actualValue - 1));
        }

        public int getValue() {
            return counter.get();
        }
    }

    private static class Holder {
        private volatile int val;
    }

    public static void main(String[] args) {
        Holder holder = new Holder();
        holder.val = 14;

        AtomicReference<Holder> atomicReference = new AtomicReference<>(holder);

        AtomicIntegerArray arr = new AtomicIntegerArray(new int[]{1, 2, 3, 4});



        AtomicIntegerFieldUpdater fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Holder.class, "val");
        fieldUpdater.set(holder, 100500);

        System.out.println(holder.val);


        String str = "hello";
        AtomicMarkableReference<String> markableReference = new AtomicMarkableReference<>(str, true);
        markableReference.compareAndSet("hello", "world", true, false);


        AtomicStampedReference<String> stampedReference = new AtomicStampedReference<>(str, 0);
        stampedReference.compareAndSet("hello", "word", 0, 1);
    }
}
