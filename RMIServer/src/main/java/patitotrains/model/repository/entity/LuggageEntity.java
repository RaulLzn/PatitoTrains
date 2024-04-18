package patitotrains.model.repository.entity;

import patitotrains.model.domain.Luggage;

public class LuggageEntity {
    private String id;
    private int weight;
    private String ticketId;

    public LuggageEntity(String id, int weight, String ticketId) {
        this.id = id;
        this.weight = weight;
        this.ticketId = ticketId;
    }

    public LuggageEntity(Luggage luggage) {
        this.id = luggage.getId();
        this.weight = luggage.getWeight();
        this.ticketId = luggage.getTicketId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
