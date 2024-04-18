package patitotrains.model.repository.entity;

import java.time.LocalDateTime;

public class TicketEntity {
    private String id;
    private LocalDateTime purchasDateTime;
    private String passengerId;
    private double value;
    private String contactPersonId;
    private String seatTypeId;
    private String routeId;

    // Constructor con todos los atributos
    public TicketEntity(String id, LocalDateTime purchasDateTime, String passengerId, double value,
                        String contactPersonId, String seatTypeId, String routeId) {
        this.id = id;
        this.purchasDateTime = purchasDateTime;
        this.passengerId = passengerId;
        this.value = value;
        this.contactPersonId = contactPersonId;
        this.seatTypeId = seatTypeId;
        this.routeId = routeId;
    }

    // Getters y setters
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

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(String contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    public String getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(String seatTypeId) {
        this.seatTypeId = seatTypeId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
}
