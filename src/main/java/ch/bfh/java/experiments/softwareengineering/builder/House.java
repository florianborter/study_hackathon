package ch.bfh.java.experiments.softwareengineering.builder;

class House implements HousePlan { //Product
    private String basement;
    private String structure;
    private String roof;
    private String interior;


    public void setBasement(String basement) {
        this.basement = basement;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    @Override
    public String toString() {
        return "House: Basement: '" + basement + "' Structure: '" + structure + "' Roof: '" + roof + "' Interior: '" + interior + "'";
    }
}
