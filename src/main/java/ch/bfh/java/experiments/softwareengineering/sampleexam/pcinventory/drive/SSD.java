package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.drive;

public class SSD extends Drive {
    public SSD(int price, String name, int capacityInGB) {
        super(price, name, capacityInGB);
    }

    @Override
    public void show() {
        System.out.println("SSD: " + name + " capacity: " + capacityInGB + "Gb (Price: " + calcPrice() + ")");
    }
}
