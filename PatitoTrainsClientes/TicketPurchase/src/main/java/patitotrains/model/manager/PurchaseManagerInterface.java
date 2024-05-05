package patitotrains.model.manager;

import patitotrains.model.domain.*;
import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;

import java.time.LocalTime;

public interface PurchaseManagerInterface {

    String ticketId();
    String pushLuggage(Train train, Luggage luggage);
    LinkedList<Route> gethabalibleRputes();
    Array<Station> getPersonalPath(Station departure, Station arrival);
    String[] getNamesTrainsPersonalPath(Array<Station> path);
    boolean pushTicket(Ticket ticket);
    boolean pushPersonalTicket(Ticket ticket);
    boolean validateLuggageWeight(double weight);
    LocalTime getDepartureTimeRouteByTrain(Train train);
    LocalTime getArrivalTimeRouteByTrain(Train train);
}
