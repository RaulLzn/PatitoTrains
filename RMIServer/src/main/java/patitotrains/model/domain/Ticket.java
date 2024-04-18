package patitotrains.model.domain;


import patitotrains.model.domain.types.SeatType;

import java.time.LocalDateTime;

public class Ticket {

    private  String id;
    private LocalDateTime purchasDateTime;
    private Passenger passenger;
    private double value;
    private ContactPerson contactPerson;
    private SeatType seatType;
    private Route route;

    public Ticket(String id, Passenger passenger, double value, SeatType seatType,
            Route route) {
        this.id = id;
        this.passenger = passenger;
        this.value = value;
        this.contactPerson = passenger.getContactPerson();
        this.seatType = seatType;
        this.route = route;
        purchasDateTime = LocalDateTime.now();
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public LocalDateTime getPurchasDateTime() {
        return purchasDateTime;
    }
    public void setPurchasDateTime(LocalDateTime purchasDateTime) {
        this.purchasDateTime = purchasDateTime;
    }
    public Passenger getPassenger() {
        return passenger;
    }
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public ContactPerson getContactPerson() {
        return contactPerson;
    }
    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }
    public SeatType getSeatType() {
        return seatType;
    }
    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) {
        this.route = route;
    }
    

    


}
