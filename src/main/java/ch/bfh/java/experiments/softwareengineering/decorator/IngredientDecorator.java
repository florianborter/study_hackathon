package ch.bfh.java.experiments.softwareengineering.decorator;

public abstract class IngredientDecorator implements Beverage {
    protected Beverage beverage;

    public IngredientDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public Double cost() {
        return beverage.cost();
    }

    @Override
    public String ingredients() {
        return beverage.ingredients();
    }
}
