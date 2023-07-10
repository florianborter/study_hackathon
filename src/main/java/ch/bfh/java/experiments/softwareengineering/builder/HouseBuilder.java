package ch.bfh.java.experiments.softwareengineering.builder;


public interface HouseBuilder { //This is the abstract Builder

    HouseBuilder buildBasement();

    HouseBuilder buildStructure();

    HouseBuilder buildRoof();

    HouseBuilder buildInterior();

    House getHouse();
}
