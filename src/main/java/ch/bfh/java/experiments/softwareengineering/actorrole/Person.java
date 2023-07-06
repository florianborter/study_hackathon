package ch.bfh.java.experiments.softwareengineering.actorrole;

public class Person extends Actor {
    public Person(Role role, String name) {
        super(role, name);
    }

    @Override
    public void work() {
        System.out.println("I am " + name);
        role.doWork();
    }
}
