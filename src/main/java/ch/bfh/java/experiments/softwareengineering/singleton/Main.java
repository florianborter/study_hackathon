package ch.bfh.java.experiments.softwareengineering.singleton;

public class Main {
    public static void main(String[] args) {
        var first = MySingleton.getInstance();
        var second = SingletonTest.test(); //see if its the same instance in another class

        System.out.println("Are they the same? '" + (first == second) + "' Object location: first: '" + first + "' second: '" + second + "'");
    }
}

class SingletonTest {
    static MySingleton test() {
        return MySingleton.getInstance();
    }
}