package upb.administrator.model.domain;

import java.time.LocalDateTime;

import com.edu.upb.linkedList.doubly.LinkedList;

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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Train getTrain() {
        return train;
    }
    public void setTrain(Train train) {
        this.train = train;
    }
    public LinkedList<Station> getStations() {
        return stations;
    }
    public void setStations(LinkedList<Station> stations) {
        this.stations = stations;
    }
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public double getRouteDistance() {
        return routeDistance;
    }
    public void setRouteDistance(double routeDistance) {
        this.routeDistance = routeDistance;
    }
    
    
}
