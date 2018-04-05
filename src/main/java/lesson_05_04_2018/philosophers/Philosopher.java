package lesson_05_04_2018.philosophers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Philosopher extends Thread {

    private int sectionIndex;
    private Runnable behaviour;


    public Philosopher(String name) {
        super(name);
    }

    @Override
    public void run() {
        behaviour.run();
    }

    public void inviteTo(Table table) {
        table.sit(this);
        start();
    }
}
