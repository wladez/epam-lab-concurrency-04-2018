package lesson_12_04_2018;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example7 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(1, 2, 3, 4));

        System.out.println(list.get(0));
        System.out.println(list.get(1));

        service.submit(() -> {
            list.add(10);
        });

        System.out.println(list.get(2));
        System.out.println(list.get(2));
        System.out.println(list.get(2));
        System.out.println(list.get(2));
        System.out.println(list.get(2));

        Set<Integer> set = new CopyOnWriteArraySet<>();
    }
}
