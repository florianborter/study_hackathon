package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.drive;

public class HDD extends Drive {
    public HDD(int price, String name, int capacityInGB) {
        super(price, name, capacityInGB);
    }

    @Override
    public void show() {
        System.out.println("HDD: " + name + " capacity: " + capacityInGB + "Gb (Price: " + calcPrice() + ")");
    }
}
