package patitotrains.model.repository.entity;

import patitotrains.model.domain.ContainerWagon;
import patitotrains.model.domain.Luggage;

import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;

import java.io.Serializable;

public class ContainerWagonEntity implements Serializable {
    private String id;
    private LuggageEntity[] luggages;

    /**
     * Creates a new container wagon entity.
     *
     * @param id the container wagon's id
     */
    public ContainerWagonEntity(String id) {
        this.id = id;
        this.luggages = new LuggageEntity[0]; // Initialize with an empty array
    }

    /**
     * Creates a new container wagon entity.
     *
     * @param id       the container wagon's id
     * @param luggages the container wagon's luggages
     */
    public ContainerWagonEntity(String id, LuggageEntity[] luggages) {
        this.id = id;
        this.luggages = luggages.clone(); // Copy the array to prevent external modification
    }

    /**
     * Creates a new container wagon entity from a container wagon domain object.
     *
     * @param containerWagon the container wagon domain object
     */
    public ContainerWagonEntity(ContainerWagon containerWagon) {
        this.id = containerWagon.getId();
        this.luggages = convertLuggageListToEntityArray(containerWagon.getLuggages()); // Initialize with an empty array
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LuggageEntity[] getLuggages() {
        return luggages;
    }

    public void setLuggages(LuggageEntity[] luggages) {
        this.luggages = luggages.clone(); // Copy the array to prevent external modification
    }

    /**
     * Converts the list of luggage from the domain object to an array of luggage entities.
     *
     * @param luggageList the list of luggage from the domain object
     * @return an array of luggage entities
     */
    private LuggageEntity[] convertLuggageListToEntityArray(LinkedList<Luggage> luggageList) {
        LuggageEntity[] entityArray = new LuggageEntity[luggageList.size()];
        Iterator<Luggage> iterator = luggageList.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            entityArray[index++] = new LuggageEntity(iterator.next());
        }
        return entityArray;
    }

    /**
     * Converts this entity to a domain object.
     *
     * @return the domain object
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ContainerWagonEntity{id='").append(id).append("', luggages=[");
        for (int i = 0; i < luggages.length; i++) {
            stringBuilder.append(luggages[i]);
            if (i < luggages.length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]}");
        return stringBuilder.toString();
    }
}
