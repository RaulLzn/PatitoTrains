package patitotrains.model.repository.entity.typesEntity;

public class SeatTypeEntity {
    private String id;
    private String description;
    private double costPerKm;

    public SeatTypeEntity(String id, String description, double costPerKm) {
        this.id = id;
        this.description = description;
        this.costPerKm = costPerKm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCostPerKm() {
        return costPerKm;
    }

    public void setCostPerKm(double costPerKm) {
        this.costPerKm = costPerKm;
    }
}
