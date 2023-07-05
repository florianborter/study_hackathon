package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.iodevice;

public class Monitor extends IoDevice{
    public Monitor(int price, String name) {
        super(price, name);
    }

    @Override
    public void show() {
        super.show();
        System.out.println("Monitor: " + name + " (Price: " + calcPrice() + ")");
    }
}
