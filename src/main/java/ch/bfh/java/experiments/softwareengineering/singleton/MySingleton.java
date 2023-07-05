package ch.bfh.java.experiments.softwareengineering.singleton;

public class MySingleton {
    private MySingleton() {
    }

    static MySingleton instance;

    public static MySingleton getInstance() {

        if (instance == null)
            instance = new MySingleton();

        return instance;
    }
}
