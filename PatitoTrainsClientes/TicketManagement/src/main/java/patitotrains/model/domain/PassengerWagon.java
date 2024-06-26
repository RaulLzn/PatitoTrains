package patitotrains.model.domain;


import raul.Model.linkedlist.doubly.circular.LinkedList;

import java.io.Serializable;

public class PassengerWagon implements Serializable {
    private String id;
    private LinkedList<Passenger> premiumPassengers;
    private LinkedList<Passenger> executivePassengers;
    private LinkedList<Passenger> standardPassengers;
    private boolean firstWagon;

    public PassengerWagon(String id, boolean firstWagon) {
        this.id = id;
        premiumPassengers = new LinkedList<>();
        executivePassengers = new LinkedList<>();
        standardPassengers = new LinkedList<>();
        this.firstWagon = firstWagon;
       
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public LinkedList<Passenger> getPremiumPassengers() {
        return premiumPassengers;
    }
    public void setPremiumPassengers(LinkedList<Passenger> premiumPassengers) {
        this.premiumPassengers = premiumPassengers;
    }
    public LinkedList<Passenger> getExecutivePassengers() {
        return executivePassengers;
    }
    public void setExecutivePassengers(LinkedList<Passenger> executivePassengers) {
        this.executivePassengers = executivePassengers;
    }
    public LinkedList<Passenger> getStandardPassengers() {
        return standardPassengers;
    }
    public void setStandardPassengers(LinkedList<Passenger> standardPassengers) {
        this.standardPassengers = standardPassengers;
    }
    public boolean isFirstWagon() {
        return firstWagon;
    }
    public void setFirstWagon(boolean firstWagon) {
        this.firstWagon = firstWagon;
    }

    @Override
    public String toString() {
        return "PassengerWagon{" +
                "id='" + id + '\'' +
                ", premiumPassengers=" + premiumPassengers +
                ", executivePassengers=" + executivePassengers +
                ", standardPassengers=" + standardPassengers +
                ", firstWagon=" + firstWagon +
                '}';
    }
    
}
