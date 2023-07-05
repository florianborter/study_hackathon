package ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory;

import ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.drive.HDD;
import ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.drive.SSD;
import ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.iodevice.Keyboard;
import ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.iodevice.Monitor;
import ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.iodevice.Mouse;
import ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.mainboardcpucombo.MainboardCpuCombo;
import ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.pcicard.EthernetCard;
import ch.bfh.java.experiments.softwareengineering.sampleexam.pcinventory.pcicard.GPU;

public class Inventory {
    public void buildInventory() {
        Computer computer = new Computer(0, "PC1");

        var monitor = new Monitor(1000, "27\" 4K LCD-Monitor");
        computer.add(monitor);

        var mouse = new Mouse(25, "USB-Mouse");
        computer.add(mouse);

        var keyboard = new Keyboard(80, "USB-RGB-Keyboard");
        computer.add(keyboard);

        var cabinet = new Cabinet(125, "Corsair 5000d Airflow");
        computer.add(cabinet);

        var ssd = new SSD(145, "Samsung 980 pro 1 TB", 1024);
        cabinet.add(ssd);

        var hdd1 = new HDD(125, "Seagate Barracuda 1TB", 1024);
        cabinet.add(hdd1);

        var hdd2 = new HDD(125, "WD Blue 2TB", 2048);
        cabinet.add(hdd2);

        var networkCard = new EthernetCard(75, "Realtek 10Gb", 10);
        cabinet.add(networkCard);

        var gpu = new GPU(1600, "Nvidia RTX 4090");
        cabinet.add(gpu);

        var mainboardCpu = new MainboardCpuCombo(895, "Core i9 13900ks");
        cabinet.add(mainboardCpu);

        computer.show();
    }
}
