package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.pcicard;

public class EthernetCard extends PciCard {
    private final int speedInGb;

    public EthernetCard(int price, String name, int speedInGb) {
        super(price, name);
        this.speedInGb = speedInGb;
    }

    @Override
    public void show() {
        System.out.println("Ethernet Card: " + name + " with speed " + speedInGb + "Gb/s (Price: " + calcPrice() + ")");
    }
}
