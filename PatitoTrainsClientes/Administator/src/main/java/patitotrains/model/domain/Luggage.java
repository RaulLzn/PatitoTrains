package patitotrains.model.domain;

import java.io.Serializable;

public class Luggage implements Serializable {
    private String id;
    private int weight;
    private String ticketId;



    public Luggage(String id, int weight, String ticketId) {
        this.id = id;
        this.weight = weight;
        this.ticketId = ticketId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Luggage(String id, String ticketId, int weight) {
        this.id = id;
        this.ticketId = ticketId;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Luggage{" +
                "id='" + id + '\'' +
                ", weight=" + weight +
                ", ticketId='" + ticketId + '\'' +
                '}';
    }
}
