package patitotrains.model.domain;

import raul.Model.linkedlist.singly.LinkedList;

public class PassengerWagon extends AbstractWagon{
    private LinkedList<Passenger> premiumPassengers;
    private LinkedList<Passenger> regularPassengers;
    private LinkedList<Passenger> economicPassengers;
    private boolean firstWagon;

    public PassengerWagon(String id, LinkedList<Passenger> premiumPassengers, LinkedList<Passenger> regularPassengers, LinkedList<Passenger> economicPassengers, boolean firstWagon) {
        super(id);
        this.premiumPassengers = premiumPassengers;
        this.regularPassengers = regularPassengers;
        this.economicPassengers = economicPassengers;
        this.firstWagon = firstWagon;
    }
}
