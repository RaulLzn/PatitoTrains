package patitotrains.model.remote.interfaces;

import patitotrains.model.domain.Station;
import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.list.List;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StationServiceRemote extends Remote {

    Station getStationById(String id) throws RemoteException;
    LinkedList<Station> getStationsByIds(Array<String> ids) throws RemoteException;
    boolean saveStation(Station station) throws RemoteException;
    boolean updateStation(Station station) throws RemoteException;
    boolean deleteStation(String id) throws RemoteException;
    List<Station> getAllStations() throws RemoteException;
}
