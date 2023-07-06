package ch.bfh.java.experiments.softwareengineering.builder;

public class Main {
    public static void main(String[] args) {
        HouseBuilder iglooBuilder = new IglooHouseBuilder();
        CivilEngineer engineer = new CivilEngineer(iglooBuilder);

        engineer.constructHouse();

        House house = engineer.getHouse();

        System.out.println("Builder constructed: " + house);


        HouseBuilder tipiBuilder = new TipiHouseBuilder();
        CivilEngineer tipiEngineer = new CivilEngineer(tipiBuilder);

        tipiEngineer.constructHouse();

        House tipi = tipiEngineer.getHouse();

        System.out.println("Builder constructed: " + tipi);


    }
}
