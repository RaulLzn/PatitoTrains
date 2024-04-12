package upb.ticketmanagement.model.domain;

import com.edu.upb.array.Array;

import upb.ticketmanagement.model.domain.types.TrainType;

public class Train {
    
    private String id;
    private String name;
    private double mileage;
    private TrainType type;
    private Array<PassengerWagon> passengerWagons;
    private Array<ContainerWagon> luggageWagons;
    private boolean onJourney;
    private boolean disabled;

    public Train(String id, String name, TrainType type,
        int amtPassengerWagons,  int amtLuggageWagons) {
        this.id = id;
        this.name = name;
        this.type = type;
        onJourney = false;
        disabled = false;
        mileage = 0;
        passengerWagons = new Array<>(amtPassengerWagons);
        luggageWagons = new Array<>(amtLuggageWagons);
    }


    public double getMileage() {
        return mileage;
    }


    public void setMileage(double mileage) {
        this.mileage = mileage;
    }


    public TrainType getType() {
        return type;
    }


    public void setType(TrainType type) {
        this.type = type;
    }


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
    public Array<PassengerWagon> getPassengerWagons() {
        return passengerWagons;
    }
    public void setPassengerWagons(Array<PassengerWagon> passengerWagons) {
        this.passengerWagons = passengerWagons;
    }
    public Array<ContainerWagon> getLuggageWagons() {
        return luggageWagons;
    }
    public void setLuggageWagons(Array<ContainerWagon> luggageWagons) {
        this.luggageWagons = luggageWagons;
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
