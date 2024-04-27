package patitotrains.model.domain.types;

import java.io.Serializable;

public class SeatType extends AbstractType implements Serializable {
    private double costPerKm;

    public SeatType(String id, String description, double costPerKm) {
        super(id, description);
        this.costPerKm = costPerKm;
    }

    public double getCostPerKm() {
        return costPerKm;
    }

    public void setCostPerKm(double costPerKm) {
        this.costPerKm = costPerKm;
    }

    @Override
    public String toString() {
        return "SeatType{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", costPerKm=" + costPerKm +
                '}';
    }

}
