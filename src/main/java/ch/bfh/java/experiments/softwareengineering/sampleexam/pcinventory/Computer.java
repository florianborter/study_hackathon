package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory;

public class Computer extends PCComposite {
    public Computer(int price, String name) {
        super(price, name);
    }

    @Override
    public void show() {
        System.out.println("Computer with name " + name + " has the following components in it:");
        super.show();
        System.out.println("Your PC has the price: " + calcPrice() + "\n");
    }
}
