package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.drive;

import ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.PCComponent;

public abstract class Drive extends PCComponent {
    protected int capacityInGB;

    public Drive(int price, String name, int capacityInGB) {
        super(price, name);
        this.capacityInGB = capacityInGB;
    }

    @Override
    public void show() {
        System.out.print("Drive: ");
    }

    @Override
    public float calcPrice() {
        return price;
    }
}
