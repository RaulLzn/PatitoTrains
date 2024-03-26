package patitotrains.model.domain;

import java.time.LocalDateTime;

public class Ticket {

    private String idTicket;
    private LocalDateTime purchaseDate;
    private Passenger passenger;
    private int value;
    private ContactPerson contactPerson;
    private SeatType seatType;
    private Route route;

    //constructor con parametros
    public Ticket(String idTicket, LocalDateTime purchaseDate, Passenger passenger, int value, ContactPerson contactPerson, SeatType seatType, Route route) {
        this.idTicket = idTicket;
        this.purchaseDate = purchaseDate;
        this.passenger = passenger;
        this.value = value;
        this.contactPerson = contactPerson;
        this.seatType = seatType;
        this.route = route;
    }

    //Constructor vacio
    public Ticket() {
        this.idTicket = "";
        this.purchaseDate = LocalDateTime.now();
        this.passenger = Passenger.getEmptyPassenger();
        this.value = 0;
        this.contactPerson = ContactPerson.getEmptyContactPerson();
        this.seatType = SeatType.NULL;;
        this.route = Route.getEmptyRoute();
    }

    //Getters y Setters
    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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

    //Devolver constructor vacio
    public static Ticket getEmptyTicket() {
        return new Ticket();
    }

}
