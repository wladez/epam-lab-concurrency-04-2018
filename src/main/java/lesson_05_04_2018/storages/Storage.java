package lesson_05_04_2018.storages;

import java.util.concurrent.TimeUnit;

public class Storage {
    private String value;

    public void setValue(String value) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        this.value = value;
    }

    public String getValue() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return value;
    }
}
