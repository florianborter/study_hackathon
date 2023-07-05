package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory;

public class Cabinet extends PCComposite {
    public Cabinet(int price, String name) {
        super(price, name);
    }

    @Override
    public void show() {
        System.out.println("Cabinet '" + name + "' has the following components in it:");
        super.show();
        System.out.println("Cabinet incl. Components cost " + calcPrice() + "\n");
    }

    @Override
    public float calcPrice() {
        return price + super.calcPrice();
    }
}
