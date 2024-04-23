package patitotrains.model.domain.types;

import java.io.Serializable;

public class TrainType extends AbstractType implements Serializable {
    private int cargoCapacity;

    public TrainType(String id, String description, int cargoCapacity) {
        super(id, description);
        this.cargoCapacity = cargoCapacity;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public String toString() {
        return "TrainType{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", cargoCapacity=" + cargoCapacity +
                '}';
    }



}
