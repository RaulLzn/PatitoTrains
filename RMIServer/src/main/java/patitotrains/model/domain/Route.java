package patitotrains.model.domain;



import raul.Model.linkedlist.doubly.circular.LinkedList;

import java.time.LocalDateTime;

public class Route {
    private String name;
    private Train train;
    private LinkedList<Station> stations;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double routeDistance;


    public Route(String name, Train train, LinkedList<Station> stations, LocalDateTime departureTime,
            LocalDateTime arrivalTime, double routeDistance) {
        this.name = name;
        this.train = train;
        this.stations = stations;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.routeDistance = routeDistance;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
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

    public LinkedList<Station> getStations() {
        return stations;
    }

    public void setStations(LinkedList<Station> stations) {
        this.stations = stations;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @Override
    public String toString() {
        return "Route{" +
                "name='" + name + '\'' +
                ", train=" + train +
                ", stations=" + stations +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", routeDistance=" + routeDistance +
                '}';
    }
}
