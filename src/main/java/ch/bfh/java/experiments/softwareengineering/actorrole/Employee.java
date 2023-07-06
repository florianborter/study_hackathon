package ch.bfh.java.experiments.softwareengineering.actorrole;

public class Employee implements Role {
    @Override
    public void doWork() {
        System.out.println("I am working (employee)");
    }
}
