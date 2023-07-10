package ch.bfh.java.experiments.softwareengineering.builder;

public class IglooHouseBuilder implements HouseBuilder { //Concrete builder
    private final House house;

    public IglooHouseBuilder() {
        house = new House();
    }

    @Override
    public HouseBuilder buildBasement() {
        house.setBasement("Ice bars");
        return this;
    }

    @Override
    public HouseBuilder buildStructure() {
        house.setStructure("Ice blocks");
        return this;
    }

    @Override
    public HouseBuilder buildRoof() {
        house.setRoof("Ice Carvings");
        return this;
    }

    @Override
    public HouseBuilder buildInterior() {
        house.setInterior("Ice Dome");
        return this;
    }

    @Override
    public House getHouse() {
        return house;
    }
}
