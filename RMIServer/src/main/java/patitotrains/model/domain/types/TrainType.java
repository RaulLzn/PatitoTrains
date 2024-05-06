package patitotrains.model.domain.types;

import java.io.Serializable;

/**
 * Clase que representa un tipo de tren.
 */
public class TrainType extends AbstractType implements Serializable {
    private int cargoCapacity;

    /**
     * Constructor de la clase.
     *
     * @param id          ID del tipo.
     * @param description Descripción del tipo.
     * @param cargoCapacity Capacidad de carga.
     */
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
