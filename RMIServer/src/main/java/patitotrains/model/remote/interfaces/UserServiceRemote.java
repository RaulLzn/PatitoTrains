package patitotrains.model.remote.interfaces;

import patitotrains.model.domain.User;
import raul.Model.util.list.List;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserServiceRemote extends Remote {

    List<User> getAllUsers() throws RemoteException;
    User getUserById(String id) throws RemoteException;
    boolean saveUser(User user) throws RemoteException;
    boolean updateUser(User user) throws RemoteException;
    boolean deleteUser(String id) throws RemoteException;
    boolean verifyUser(String userName, String password) throws RemoteException;
}
