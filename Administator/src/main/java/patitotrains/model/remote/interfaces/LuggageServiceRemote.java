package patitotrains.model.remote.interfaces;


import raul.Model.util.list.List;
import patitotrains.model.domain.Luggage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LuggageServiceRemote extends Remote {
    List<Luggage> getAllLuggage() throws RemoteException;
    boolean saveLuggage(Luggage luggage) throws RemoteException;
    boolean updateLuggage(Luggage luggage) throws RemoteException;
    boolean deleteLuggage(String id) throws RemoteException;
    Luggage getLuggageById(String id) throws RemoteException;
}
