package ch.bfh.java.experiments.softwareengineering.actorrole;

public class Main {
    public static void main(String[] args) {
        Role manager = new Manager();
        Role employee = new Employee();

        Actor john = new Person(employee, "John");
        Actor alice = new Person(manager, "Alice");
        Actor luca = new Person(employee, "Luca");

        john.work();
        alice.work();
        luca.work();

        //Promote john to Manager:
        john.setRole(manager);
        System.out.println("John promoted!");
        john.work();
    }
}
