package ch.bfh.java.experiments.softwareengineering.decorator;

public class Mocha extends BeverageDecorator {
    public Mocha(Beverage beverage) {
        super(beverage);
    }

    @Override
    public Double cost() {
        return super.cost() + withMochaPrice();
    }

    private Double withMochaPrice() {
        return 2.0;
    }

    @Override
    public String ingredients() {
        return super.ingredients() + " with Mocha";
    }
}
