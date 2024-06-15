package patitotrains.model.repository.entity;

import patitotrains.model.domain.Route;
import patitotrains.model.domain.Station;
import patitotrains.model.domain.Train;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;

import java.io.Serializable;

public class RouteEntity implements Serializable {
    private String id;
    private String name;
    private String[] trainIds;
    private String[] stationIds;
    private String departureTime;
    private String arrivalTime;
    private double routeDistance;
    private boolean disabled;

    public RouteEntity(String id, String name, String[] trainIds, String[] stationIds,
                       String departureTime, String arrivalTime, double routeDistance, boolean disabled) {
        this.id = id;
        this.name = name;
        this.trainIds = trainIds;
        this.stationIds = stationIds;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.routeDistance = routeDistance;
        this.disabled = disabled;
    }

    public RouteEntity(Route route) {
        this.id = route.getId();
        this.name = route.getName();
        this.trainIds = convertTrainListToIdArray(route.getTrains());
        this.stationIds = convertStationListToIdArray(route.getStations());
        this.departureTime = route.getDepartureTime().toString(); // Convertir LocalTime a String
        this.arrivalTime = route.getArrivalTime().toString(); // Convertir LocalTime a String
        this.routeDistance = route.getRouteDistance();
        this.disabled = route.isDisabled();
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
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

    public double getRouteDistance() {
        return routeDistance;
    }

    public void setRouteDistance(double routeDistance) {
        this.routeDistance = routeDistance;
    }

    public String[] getStationIds() {
        return stationIds;
    }

    public void setStationIds(String[] stationIds) {
        this.stationIds = stationIds;
    }

    public String[] getTrainIds() {
        return trainIds;
    }

    public void setTrainIds(String[] trainIds) {
        this.trainIds = trainIds;
    }

    /**
     * Converts a list of trains to an array of train ids.
     *
     * @param trains the list of trains to convert
     * @return the array of train ids
     */
    private String[] convertTrainListToIdArray(LinkedList<Train> trains) {
        String[] idArray = new String[trains.size()];
        Iterator<Train> iterator = trains.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Train train = iterator.next();
            idArray[index++] = train.getId();
        }
        return idArray;
    }

    /**
     * Converts a list of stations to an array of station ids.
     *
     * @param stations the list of stations to convert
     * @return the array of station ids
     */
    private String[] convertStationListToIdArray(LinkedList<Station> stations) {
        String[] idArray = new String[stations.size()];
        Iterator<Station> iterator = stations.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Station station = iterator.next();
            idArray[index++] = station.getId();
        }
        return idArray;
    }

    @Override
    public String toString() {
        return "RouteEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", trainIds=" + arrayToString(trainIds) +
                ", stationIds=" + arrayToString(stationIds) +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", routeDistance=" + routeDistance +
                ", disabled=" + disabled +
                '}';
    }

    /**
     * Converts an array to a string.
     *
     * @param array the array to convert
     * @return the string representation of the array
     */
    private String arrayToString(String[] array) {
        if (array == null) {
            return "null";
        }
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]);
            if (i < array.length - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

}
