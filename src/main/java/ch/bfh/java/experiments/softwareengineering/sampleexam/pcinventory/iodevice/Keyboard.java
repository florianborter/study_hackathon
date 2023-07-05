package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.iodevice;

public class Keyboard extends IoDevice{
    public Keyboard(int price, String name) {
        super(price, name);
    }

    @Override
    public void show() {
        super.show();
        System.out.println("Keyboard: " + name + " (Price: " + calcPrice() + ")");
    }
}
