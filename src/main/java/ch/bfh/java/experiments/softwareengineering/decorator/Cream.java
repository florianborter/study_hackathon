package ch.bfh.java.experiments.softwareengineering.decorator;

public class Cream extends IngredientDecorator {
    public Cream(Beverage beverage) {
        super(beverage);
    }

    @Override
    public Double cost() {
        return super.cost() + withCreamPrice();
    }

    private Double withCreamPrice() {
        return 1.0;
    }

    @Override
    public String ingredients() {
        return super.ingredients() + " with Cream";
    }
}
