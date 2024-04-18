package patitotrains.model.repository.entity;

import patitotrains.model.domain.types.TrainType;

public class TrainEntity {
    private String id;
    private String name;
    private double mileage;
    private String type;
    private int amtPassengerWagons;
    private int amtLuggageWagons;
    private boolean onJourney;
    private boolean disabled;

    // Constructor con todos los atributos
    public TrainEntity(String id, String name, double mileage, String type, int amtPassengerWagons,
                       int amtLuggageWagons, boolean onJourney, boolean disabled) {
        this.id = id;
        this.name = name;
        this.mileage = mileage;
        this.type = type;
        this.amtPassengerWagons = amtPassengerWagons;
        this.amtLuggageWagons = amtLuggageWagons;
        this.onJourney = onJourney;
        this.disabled = disabled;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmtPassengerWagons() {
        return amtPassengerWagons;
    }

    public void setAmtPassengerWagons(int amtPassengerWagons) {
        this.amtPassengerWagons = amtPassengerWagons;
    }

    public int getAmtLuggageWagons() {
        return amtLuggageWagons;
    }

    public void setAmtLuggageWagons(int amtLuggageWagons) {
        this.amtLuggageWagons = amtLuggageWagons;
    }

    public boolean isOnJourney() {
        return onJourney;
    }

    public void setOnJourney(boolean onJourney) {
        this.onJourney = onJourney;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
