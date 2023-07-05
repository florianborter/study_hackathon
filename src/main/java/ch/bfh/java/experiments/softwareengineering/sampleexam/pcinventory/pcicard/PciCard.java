package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.pcicard;

import ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.PCComponent;

public abstract class PciCard extends PCComponent {
    public PciCard(int price, String name) {
        super(price, name);
    }
    
    @Override
    public float calcPrice() {
        return price;
    }
}
