package ch.bfh.java.experiments.softwareengineering.builder;

class CivilEngineer { //Director class
    private final HouseBuilder houseBuilder;

    public CivilEngineer(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public House getHouse() {
        return houseBuilder.getHouse();
    }

    public void constructHouse() {
        houseBuilder.buildBasement()
                .buildStructure()
                .buildRoof()
                .buildInterior();
    }
}
