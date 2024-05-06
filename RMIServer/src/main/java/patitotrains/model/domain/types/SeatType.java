package patitotrains.model.domain.types;

import java.io.Serializable;

/**
 * Clase que representa un tipo de asiento.
 */
public class SeatType extends AbstractType implements Serializable {
    private double costPerKm;

    /**
     * Constructor de la clase.
     *
     * @param id          ID del tipo.
     * @param description Descripción del tipo.
     * @param costPerKm   Costo por kilómetro.
     */
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
