package patitotrains.model.repository.entity;

import raul.Model.linkedlist.doubly.circular.LinkedList;

import java.time.LocalDateTime;

public class RouteEntity {
    private String name;
    private String trainId; // Cambiar por el tipo de dato adecuado según cómo se almacenen los trenes en la base de datos
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double routeDistance;

    // Constructor con todos los atributos
    public RouteEntity(String name, String trainId, LinkedList<String> stationIds, LocalDateTime departureTime, LocalDateTime arrivalTime, double routeDistance) {
        this.name = name;
        this.trainId = trainId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.routeDistance = routeDistance;
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
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
