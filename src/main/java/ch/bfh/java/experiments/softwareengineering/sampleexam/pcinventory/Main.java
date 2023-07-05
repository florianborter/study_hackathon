package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory;

public class Main {

    //This Program uses the composite design pattern
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.buildInventory();
    }
}
