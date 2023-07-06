package ch.bfh.java.experiments.softwareengineering.decorator;

public class DarkRoast extends Coffee {

    @Override
    public Double cost() {
        return 10.0;
    }

    @Override
    public String ingredients() {
        return "Dark roast";
    }
}
