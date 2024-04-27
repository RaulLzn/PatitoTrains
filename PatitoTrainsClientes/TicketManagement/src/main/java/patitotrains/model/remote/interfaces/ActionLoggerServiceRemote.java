package patitotrains.model.remote.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ActionLoggerServiceRemote extends Remote {
    boolean logAction(String action) throws RemoteException;
}

