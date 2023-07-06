package ch.bfh.java.experiments.softwareengineering.builder;


public interface HouseBuilder { //This is the abstract Builder

    public void buildBasement();

    public void buildStructure();

    public void buildRoof();

    public void buildInterior();

    public House getHouse();
}
