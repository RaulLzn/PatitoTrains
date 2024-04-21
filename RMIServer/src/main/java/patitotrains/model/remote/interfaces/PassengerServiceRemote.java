package patitotrains.model.remote.interfaces;

import patitotrains.model.domain.Passenger;
import raul.Model.util.list.List;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PassengerServiceRemote extends Remote {
    List<Passenger> getAllPassengers() throws RemoteException;
    boolean savePassenger(Passenger passenger) throws RemoteException;
    Passenger getPassengerById(String id) throws RemoteException;
    boolean deletePassenger(String id) throws RemoteException;
    boolean updatePassenger(Passenger passenger) throws RemoteException;
}
