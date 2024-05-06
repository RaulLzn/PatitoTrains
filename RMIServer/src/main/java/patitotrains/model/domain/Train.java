package patitotrains.model.domain;


import patitotrains.model.domain.types.TrainType;
import raul.Model.array.Array;

import java.io.Serializable;
/**
 * Clase que representa un tren.
 */
public class Train implements Serializable {

    private String id;
    private String name;
    private double mileage;
    private TrainType type;
    private Array<PassengerWagon> passengerWagons;
    private Array<ContainerWagon> luggageWagons;
    private boolean onJourney;
    private boolean disabled;

    /**
     * Constructor de la clase.
     *
     * @param id ID del tren.
     * @param name Nombre del tren.
     * @param type Tipo del tren.
     * @param amtPassengerWagons Cantidad de vagones de pasajeros.
     * @param amtLuggageWagons Cantidad de vagones de equipaje.
     */
    public Train(String id, String name, TrainType type,
                 int amtPassengerWagons, int amtLuggageWagons) {
        this.id = id;
        this.name = name;
        this.type = type;
        onJourney = false;
        disabled = false;
        mileage = 0;
        passengerWagons = new Array<>(amtPassengerWagons);
        luggageWagons = new Array<>(amtLuggageWagons);
    }

    public Train(String id, String name, double mileage, TrainType type, Array<PassengerWagon> passengerWagons, Array<ContainerWagon> luggageWagons, boolean onJourney, boolean disabled) {
        this.id = id;
        this.name = name;
        this.mileage = mileage;
        this.type = type;
        this.passengerWagons = passengerWagons;
        this.luggageWagons = luggageWagons;
        this.onJourney = onJourney;
        this.disabled = disabled;
    }



    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Array<ContainerWagon> getLuggageWagons() {
        return luggageWagons;
    }

    public void setLuggageWagons(Array<ContainerWagon> luggageWagons) {
        this.luggageWagons = luggageWagons;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnJourney() {
        return onJourney;
    }

    public void setOnJourney(boolean onJourney) {
        this.onJourney = onJourney;
    }

    public Array<PassengerWagon> getPassengerWagons() {
        return passengerWagons;
    }

    public void setPassengerWagons(Array<PassengerWagon> passengerWagons) {
        this.passengerWagons = passengerWagons;
    }

    public TrainType getType() {
        return type;
    }

    public void setType(TrainType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Train [disabled=" + disabled + ", id=" + id + ", luggageWagons=" + luggageWagons + ", mileage=" + mileage
                + ", name=" + name + ", onJourney=" + onJourney + ", passengerWagons=" + passengerWagons + ", type=" + type
                + "]";
    }


}
