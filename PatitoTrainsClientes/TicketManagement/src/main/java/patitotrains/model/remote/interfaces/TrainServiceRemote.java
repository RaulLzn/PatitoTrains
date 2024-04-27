package patitotrains.model.remote.interfaces;


import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.list.List;

import patitotrains.model.domain.ContainerWagon;
import patitotrains.model.domain.PassengerWagon;
import patitotrains.model.domain.Train;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TrainServiceRemote extends Remote {

    Train getTrainById(String id) throws RemoteException;
    boolean saveTrain(Train train) throws RemoteException;
    boolean updateTrain(Train train) throws RemoteException;
    boolean deleteTrain(String id) throws RemoteException;
    boolean addPassengerWagonToTrain(String trainId, PassengerWagon passengerWagon) throws RemoteException;
    boolean addContainerWagonToTrain(String trainId, ContainerWagon containerWagon) throws RemoteException;
    List<Train> getAllTrains() throws RemoteException;
    LinkedList<Train> getTrainsByIds(Array<String> ids) throws RemoteException;
}
