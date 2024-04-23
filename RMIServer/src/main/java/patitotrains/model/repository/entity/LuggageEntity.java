package patitotrains.model.repository.entity;

import patitotrains.model.domain.Luggage;

import java.io.Serializable;

public class LuggageEntity implements Serializable {
    private String id;
    private int weight;
    private String ticketId;

    /**
     * Creates a new luggage entity.
     *
     * @param id       the luggage's id
     * @param weight   the luggage's weight
     * @param ticketId the luggage's ticket id
     */
    public LuggageEntity(String id, int weight, String ticketId) {
        this.id = id;
        this.weight = weight;
        this.ticketId = ticketId;
    }

    /**
     * Creates a new luggage entity from a luggage domain object.
     *
     * @param luggage the luggage domain object
     */
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

    /**
     * Converts this entity to a domain object.
     *
     * @return the domain object
     */
    @Override
    public String toString() {
        return "LuggageEntity{" +
                "id='" + id + '\'' +
                ", weight=" + weight +
                ", ticketId='" + ticketId + '\'' +
                '}';
    }
}
