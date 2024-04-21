package upb.ticketmanagement.model.domain;
import java.time.LocalTime;

import com.edu.upb.linkedList.doubly.LinkedList;

public class Route {
    private String id;
    private String name;
    private LinkedList<Train> trains;
    private LinkedList<Station> stations;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private double routeDistance;
    private boolean disabled;


    public Route(String id,String name, LinkedList<Train> trains, LinkedList<Station> stations, LocalTime departureTime,
    LocalTime arrivalTime, double routeDistance) {
        this.id = id;        
        this.name = name;
        this.trains = trains;
        this.stations = stations;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.routeDistance = routeDistance;
        disabled = false;
    }

    

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
 
    public LinkedList<Station> getStations() {
        return stations;
    }
    public void setStations(LinkedList<Station> stations) {
        this.stations = stations;
    }
    public LocalTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public double getRouteDistance() {
        return routeDistance;
    }
    public void setRouteDistance(double routeDistance) {
        this.routeDistance = routeDistance;
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



    public LinkedList<Train> getTrains() {
        return trains;
    }



    public void setTrains(LinkedList<Train> trains) {
        this.trains = trains;
    }
    
    
}
