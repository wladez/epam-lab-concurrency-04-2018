package lesson_11_04_2018.stack;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingStack<T> {
    private final Object[] arr;
    private int current = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public BlockingStack(int size) {
        arr = new Object[size];
    }

    public void push(T element) throws InterruptedException {
        lock.lock();
        if(current == arr.length) {
            notFull.await();
        }
        arr[current] = element;
        current++;
        notEmpty.signal();
        lock.unlock();
    }

    @SuppressWarnings("unchecked")
    public T pop() throws InterruptedException {
        lock.lock();
        if (current == 0){
            notEmpty.await();
        }
        T toReturn = (T) arr[--current];
        notFull.signal();
        lock.unlock();
        return toReturn;
    }
}
