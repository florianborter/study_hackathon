package ch.bfh.java.experiments.softwareengineering.actorrole;

public class Manager implements Role {
    @Override
    public void doWork() {
        System.out.println("I am working (manager)");
    }
}
