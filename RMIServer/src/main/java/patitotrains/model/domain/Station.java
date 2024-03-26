package patitotrains.model.domain;

public class Station {
    private String idStation;
    private String name;

    //
    public Station(String name, String idStation) {
        this.name = name;
        this.idStation = idStation;
    }

    public Station() {
        this.name = "";
        this.idStation = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdStation() {
        return idStation;
    }

    public void setIdStation(String idStation) {
        this.idStation = idStation;
    }
}
