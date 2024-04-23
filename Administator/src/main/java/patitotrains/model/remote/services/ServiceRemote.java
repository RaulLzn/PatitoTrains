package patitotrains.model.remote.services;

import patitotrains.model.remote.interfaces.*;

import java.rmi.Remote;

public interface ServiceRemote extends ContainerWagonServiceRemote, PassengerWagonServiceRemote, RouteServiceRemote, UserServiceRemote, TrainServiceRemote, LuggageServiceRemote, StationServiceRemote, TicketServiceRemote, PassengerServiceRemote, Remote {

}
