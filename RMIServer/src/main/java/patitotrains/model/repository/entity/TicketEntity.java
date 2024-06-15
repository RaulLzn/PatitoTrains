package patitotrains.model.repository.entity;

import patitotrains.model.domain.Ticket;
import patitotrains.model.domain.types.SeatType;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TicketEntity implements Serializable {
    private String id;
    private String purchaseDateTime;
    private String[] passengerIds;
    private double value;
    private SeatType seatType;
    private String[] routeIds;

    public TicketEntity(String id, String[] passengerIds, double value, SeatType seatType, String[] routeIds) {
        this.id = id;
        this.passengerIds = passengerIds;
        this.value = value;
        this.seatType = seatType;
        this.routeIds = routeIds;
        this.purchaseDateTime = LocalDateTime.now().toString();
    }

    //constructor para la conversion de un objeto Ticket a TicketEntity
    public TicketEntity(Ticket ticket) {
        this.id = ticket.getId();
        this.passengerIds = new String[]{ticket.getPassenger().getId()};
        this.value = ticket.getValue();
        this.seatType = ticket.getSeatType();
        this.routeIds = new String[]{ticket.getRoute().getId()};
        this.purchaseDateTime = ticket.getPurchasDateTime().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(String purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }

    public String[] getPassengerIds() {
        return passengerIds;
    }

    public void setPassengerIds(String[] passengerIds) {
        this.passengerIds = passengerIds;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public String[] getRouteIds() {
        return routeIds;
    }

    public void setRouteIds(String[] routeIds) {
        this.routeIds = routeIds;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TicketEntity{");
        sb.append("id='").append(id).append('\'');
        sb.append(", purchaseDateTime=").append(purchaseDateTime);
        sb.append(", passengerIds=[");
        if (passengerIds.length > 0) {
            sb.append(passengerIds[0]);
            for (int i = 1; i < passengerIds.length; i++) {
                sb.append(", ").append(passengerIds[i]);
            }
        }
        sb.append("]");
        sb.append(", value=").append(value);
        sb.append(", seatType=").append(seatType);
        sb.append(", routeIds=[");
        if (routeIds.length > 0) {
            sb.append(routeIds[0]);
            for (int i = 1; i < routeIds.length; i++) {
                sb.append(", ").append(routeIds[i]);
            }
        }
        sb.append("]");
        sb.append('}');
        return sb.toString();
    }

}
