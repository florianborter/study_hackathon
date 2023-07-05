package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory;

public abstract class PCComponent {
    protected int price;
    protected String name;

    public PCComponent(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public abstract void show();

    public abstract float calcPrice();
}
