package patitotrains.model.repository.entity;

import patitotrains.model.domain.Station;

public class StationEntity {
    private String id;
    private String name;

    /**
     * Creates a new station entity.
     *
     * @param id   the station's id
     * @param name the station's name
     */
    public StationEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Creates a new station entity from a station domain object.
     *
     * @param station the station domain object
     */
    public StationEntity(Station station) {
        this.id = station.getId();
        this.name = station.getName();
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

    /**
     * Converts this entity to a domain object.
     *
     * @return the domain object
     */
    @Override
    public String toString() {
        return "StationEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
