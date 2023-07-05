package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.iodevice;

import ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.PCComponent;

public abstract class IoDevice extends PCComponent {
    public IoDevice(int price, String name) {
        super(price, name);
    }

    @Override
    public float calcPrice() {
        return price;
    }

    @Override
    public void show() {
        System.out.print("IO Device: ");
    }
}
