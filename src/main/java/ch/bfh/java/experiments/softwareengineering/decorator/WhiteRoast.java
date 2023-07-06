package ch.bfh.java.experiments.softwareengineering.decorator;

public class WhiteRoast extends Coffee{
    @Override
    public Double cost() {
        return 9.0;
    }

    @Override
    public String ingredients() {
        return "White roast";
    }
}
