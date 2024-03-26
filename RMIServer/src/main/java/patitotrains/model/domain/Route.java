package patitotrains.model.domain;

import raul.Model.linkedlist.singly.LinkedList;

import java.time.LocalDateTime;

public class Route {
    private String name;
    private String idRoute;;
    private LinkedList<Station> stations;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    //constructor con parametros
    public Route(String name, String idRoute, LinkedList<Station> stations, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.name = name;
        this.idRoute = idRoute;
        this.stations = stations;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    //Constructor vacio
    public Route() {
        this.name = "";
        this.idRoute = "";
        this.stations = new LinkedList<>();
        this.departureTime = LocalDateTime.now();
        this.arrivalTime = LocalDateTime.now();
    }

    //Getters y Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(String idRoute) {
        this.idRoute = idRoute;
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

    //Devolver constructor vacio
    public static Route getEmptyRoute() {
        return new Route();
    }

}
