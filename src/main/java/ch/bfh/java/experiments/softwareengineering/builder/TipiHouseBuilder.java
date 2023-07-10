package ch.bfh.java.experiments.softwareengineering.builder;

class TipiHouseBuilder implements HouseBuilder { //Concrete builder
    private final House house;

    public TipiHouseBuilder() {
        this.house = new House();
    }

    public HouseBuilder buildBasement() {
        house.setBasement("Wooden Poles");
        return this;
    }

    public HouseBuilder buildStructure() {
        house.setStructure("Wood and Ice");
        return this;
    }

    public HouseBuilder buildInterior() {
        house.setInterior("Fire Wood");
        return this;
    }

    public HouseBuilder buildRoof() {
        house.setRoof("Wood, caribou and seal skins");
        return this;
    }

    public House getHouse() {
        return this.house;
    }

}