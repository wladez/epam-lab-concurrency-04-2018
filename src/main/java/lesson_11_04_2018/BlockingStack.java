package lesson_11_04_2018;


// push
// pop
//
//
// new BlockingStack(5)
//
//
// -----
//
//
//
//
//
// ______


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

    public void push(T element) {
        // TODO
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        // TODO
        return (T) arr[current];
    }

}
