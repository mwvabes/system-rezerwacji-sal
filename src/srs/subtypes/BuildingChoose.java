package srs.subtypes;

public class BuildingChoose {

    int idBuilding;
    String name;
    String area;

    public BuildingChoose() {
    }

    public BuildingChoose(String name) {
        this.name = name;
    }

    public BuildingChoose(int idBuilding, String name, String area) {
        this.idBuilding = idBuilding;
        this.name = name;
        this.area = area;
    }

    public int getIdBuilding() {
        return idBuilding;
    }

    public void setIdBuilding(int idBuilding) {
        this.idBuilding = idBuilding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        if (area == null) {
            return name;
        } else return "" + name + " | " + area + "";
    }
}
