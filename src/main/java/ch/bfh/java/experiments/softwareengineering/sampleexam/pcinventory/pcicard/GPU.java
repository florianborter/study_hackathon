package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.pcicard;

public class GPU extends PciCard {
    public GPU(int price, String name) {
        super(price, name);
    }

    @Override
    public void show() {
        System.out.println("GPU: " + name + " (Price: " + calcPrice() + ")");
    }
}
