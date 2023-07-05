package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory;

import java.util.ArrayList;
import java.util.List;

public abstract class PCComposite extends PCComponent {
    List<PCComponent> componentList = new ArrayList<>();

    public PCComposite(int price, String name) {
        super(price, name);
    }

    public void add(PCComponent component) {
        componentList.add(component);
    }

    @Override
    public void show() {
        for (PCComponent component : componentList) {
            component.show();
        }
    }

    @Override
    public float calcPrice() {
        float sum = 0.0f;
        for (PCComponent component : componentList) {
            sum += component.calcPrice();
        }
        return sum;
    }
}
