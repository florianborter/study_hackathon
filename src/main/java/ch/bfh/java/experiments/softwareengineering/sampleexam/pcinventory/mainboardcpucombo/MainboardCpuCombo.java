package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.mainboardcpucombo;

import ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.PCComponent;

public class MainboardCpuCombo extends PCComponent {
    public MainboardCpuCombo(int price, String name) {
        super(price, name);
    }

    @Override
    public void show() {
        System.out.println("Motherboard-CPU-Combination: " + name + " (Price: " + calcPrice() + ")");
    }

    @Override
    public float calcPrice() {
        return price;
    }
}
