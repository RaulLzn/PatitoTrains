package patitotrains.model.manager;

import patitotrains.model.domain.*;
import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;

import java.time.LocalTime;

/**
 * Interfaz de la clase PurchaseManager
 */
public interface PurchaseManagerInterface {

    /**
     * Metodo que retorna el id del boleto
     * @return id del boleto
     */
    String ticketId();
    /**
     * Metodo que retorna el id del boleto
     * @return id del boleto
     */
    String pushLuggage(Train train, Luggage luggage);
    /**
     * Metodo que retorna el id del boleto
     * @return id del boleto
     */
    LinkedList<Route> gethabalibleRputes();
    /**
     * Metodo que retorna el id del boleto
     * @return id del boleto
     */
    Array<Station> getPersonalPath(Station departure, Station arrival);
    /**
     * Metodo que retorna el id del boleto
     * @return id del boleto
     */
    String[] getNamesTrainsPersonalPath(Array<Station> path);
    /**
     * Metodo que retorna el id del boleto
     * @return id del boleto
     */
    boolean pushTicket(Ticket ticket);
    /**
     * Metodo que retorna el id del boleto
     * @return id del boleto
     */
    boolean pushPersonalTicket(Ticket ticket);
    /**
     * Metodo que retorna el id del boleto
     * @return id del boleto
     */
    boolean validateLuggageWeight(double weight);
    /**
     * Metodo que retorna el id del boleto
     * @return id del boleto
     */
    LocalTime getDepartureTimeRouteByTrain(Train train);
    /**
     * Metodo que retorna el id del boleto
     * @return id del boleto
     */
    LocalTime getArrivalTimeRouteByTrain(Train train);
}
