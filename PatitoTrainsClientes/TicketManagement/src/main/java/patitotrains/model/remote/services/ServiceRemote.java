package patitotrains.model.remote.services;

import patitotrains.model.remote.interfaces.*;

import java.rmi.Remote;

/**
 * Interfaz que extiende de las interfaces de los servicios remotos
 */
public interface ServiceRemote extends ContainerWagonServiceRemote, PassengerWagonServiceRemote, RouteServiceRemote, UserServiceRemote, TrainServiceRemote, LuggageServiceRemote, StationServiceRemote, TicketServiceRemote, PassengerServiceRemote, ActionLoggerServiceRemote , Remote {

}
