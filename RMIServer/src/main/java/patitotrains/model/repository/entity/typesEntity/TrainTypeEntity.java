package patitotrains.model.repository.entity.typesEntity;

public class TrainTypeEntity {
    private String id;
    private String description;
    private int cargoCapacity;

    public TrainTypeEntity(String id, String description, int cargoCapacity) {
        this.id = id;
        this.description = description;
        this.cargoCapacity = cargoCapacity;
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

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }
}
