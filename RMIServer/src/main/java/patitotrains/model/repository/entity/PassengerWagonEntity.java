package patitotrains.model.repository.entity;

import patitotrains.model.domain.Passenger;
import patitotrains.model.domain.PassengerWagon;

import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;

import java.io.Serializable;


public class PassengerWagonEntity implements Serializable {
    private String id;
    private PassengerEntity[] premiumPassengers;
    private PassengerEntity[] executivePassengers;
    private PassengerEntity[] standardPassengers;
    private boolean firstWagon;

    /**
     * Creates a new passenger wagon entity.
     *
     * @param id                 the passenger wagon's id
     * @param firstWagon         whether the passenger wagon is the first wagon
     * @param premiumPassengers  the passenger wagon's premium passengers
     * @param executivePassengers the passenger wagon's executive passengers
     * @param standardPassengers  the passenger wagon's standard passengers
     */
    public PassengerWagonEntity(String id, boolean firstWagon, PassengerEntity[] premiumPassengers, PassengerEntity[] executivePassengers, PassengerEntity[] standardPassengers) {
        this.id = id;
        this.premiumPassengers = premiumPassengers;
        this.executivePassengers = executivePassengers;
        this.standardPassengers = standardPassengers;
        this.firstWagon = firstWagon;
    }

    /**
     * Creates a new passenger wagon entity from a passenger wagon domain object.
     *
     * @param passengerWagon the passenger wagon domain object
     */
    public PassengerWagonEntity(PassengerWagon passengerWagon) {
        this.id = passengerWagon.getId();
        this.premiumPassengers = convertPassengerListToArray(passengerWagon.getPremiumPassengers());
        this.executivePassengers = convertPassengerListToArray(passengerWagon.getExecutivePassengers());
        this.standardPassengers = convertPassengerListToArray(passengerWagon.getStandardPassengers());
        this.firstWagon = passengerWagon.isFirstWagon();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PassengerEntity[] getPremiumPassengers() {
        return premiumPassengers;
    }

    public void setPremiumPassengers(PassengerEntity[] premiumPassengers) {
        this.premiumPassengers = premiumPassengers;
    }

    public PassengerEntity[] getExecutivePassengers() {
        return executivePassengers;
    }

    public void setExecutivePassengers(PassengerEntity[] executivePassengers) {
        this.executivePassengers = executivePassengers;
    }

    public PassengerEntity[] getStandardPassengers() {
        return standardPassengers;
    }

    public void setStandardPassengers(PassengerEntity[] standardPassengers) {
        this.standardPassengers = standardPassengers;
    }

    public boolean isFirstWagon() {
        return firstWagon;
    }

    public void setFirstWagon(boolean firstWagon) {
        this.firstWagon = firstWagon;
    }

    /**
     * Converts this entity to a domain object.
     *
     * @return the domain object
     */
    private PassengerEntity[] convertPassengerListToArray(LinkedList<Passenger> passengerList) {
        PassengerEntity[] array = new PassengerEntity[passengerList.size()];
        int i = 0;
        Iterator<Passenger> iterator = passengerList.iterator();
        while (iterator.hasNext()) {
            array[i++] = new PassengerEntity(iterator.next());
        }
        return array;
    }

    /**
     * Converts this entity to a domain object.
     *
     * @return the domain object
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PassengerWagonEntity{id='").append(id).append('\'');
        builder.append(", premiumPassengers=[");
        if (premiumPassengers != null && premiumPassengers.length > 0) {
            for (int i = 0; i < premiumPassengers.length - 1; i++) {
                builder.append(premiumPassengers[i].toString()).append(", ");
            }
            builder.append(premiumPassengers[premiumPassengers.length - 1].toString());
        }
        builder.append("], executivePassengers=[");
        if (executivePassengers != null && executivePassengers.length > 0) {
            for (int i = 0; i < executivePassengers.length - 1; i++) {
                builder.append(executivePassengers[i].toString()).append(", ");
            }
            builder.append(executivePassengers[executivePassengers.length - 1].toString());
        }
        builder.append("], standardPassengers=[");
        if (standardPassengers != null && standardPassengers.length > 0) {
            for (int i = 0; i < standardPassengers.length - 1; i++) {
                builder.append(standardPassengers[i].toString()).append(", ");
            }
            builder.append(standardPassengers[standardPassengers.length - 1].toString());
        }
        builder.append("], firstWagon=").append(firstWagon).append('}');
        return builder.toString();
    }

}
