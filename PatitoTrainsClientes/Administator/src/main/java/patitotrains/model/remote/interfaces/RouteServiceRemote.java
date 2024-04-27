package patitotrains.model.remote.interfaces;


import raul.Model.util.list.List;
import patitotrains.model.domain.Route;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RouteServiceRemote extends Remote {
    List<Route> getAllRoutes() throws RemoteException;
    Route getRouteById(String id) throws RemoteException;
    boolean saveRoute(Route route) throws RemoteException;
    boolean updateRoute(Route route) throws RemoteException;
    boolean deleteRoute(String id) throws RemoteException;
    boolean disableRoute(String id) throws RemoteException;
    boolean enableRoute(String id) throws RemoteException;
}
