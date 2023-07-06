package ch.bfh.java.experiments.softwareengineering.builder;

public class IglooHouseBuilder implements HouseBuilder { //Concrete builder
    private final House house;

    public IglooHouseBuilder() {
        house = new House();
    }

    @Override
    public void buildBasement() {
        house.setBasement("Ice bars");
    }

    @Override
    public void buildStructure() {
        house.setStructure("Ice blocks");
    }

    @Override
    public void buildRoof() {
        house.setRoof("Ice Carvings");
    }

    @Override
    public void buildInterior() {
        house.setInterior("Ice Dome");
    }

    @Override
    public House getHouse() {
        return house;
    }
}
