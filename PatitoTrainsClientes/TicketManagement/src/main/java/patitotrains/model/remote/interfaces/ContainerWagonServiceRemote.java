package patitotrains.model.remote.interfaces;



import raul.Model.util.list.List;
import patitotrains.model.domain.ContainerWagon;
import patitotrains.model.domain.Luggage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ContainerWagonServiceRemote extends Remote {
    List<ContainerWagon> getAllContainerWagons() throws RemoteException;
    boolean saveContainerWagon(ContainerWagon containerWagon) throws RemoteException;
    ContainerWagon getContainerWagonById(String id) throws RemoteException;
    boolean deleteContainerWagon(String id) throws RemoteException;
    boolean updateContainerWagon(ContainerWagon containerWagon) throws RemoteException;
    boolean addLuggageToContainerWagon(String containerWagonId, Luggage luggage) throws RemoteException;
    boolean deleteLuggageFromContainerWagon(String containerWagonId, String luggageId) throws RemoteException;
}
