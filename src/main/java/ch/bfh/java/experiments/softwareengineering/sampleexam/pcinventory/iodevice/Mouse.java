package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.iodevice;

public class Mouse extends IoDevice{
    public Mouse(int price, String name) {
        super(price, name);
    }

    @Override
    public void show() {
        super.show();
        System.out.println("Mouse: " + name + " (Price: " + calcPrice() + ")");
    }
}
