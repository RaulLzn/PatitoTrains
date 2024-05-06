package patitotrains.model.Managers.interfaceManagers;

import patitotrains.model.domain.User;
import raul.Model.linkedlist.doubly.circular.LinkedList;

import java.rmi.RemoteException;

/**
 * Interfaz de la clase UserManager
 */
public interface UserManagerInterface {
    /**
     * Método que obtiene los datos de los usuarios
     * @return Lista de usuarios
     */
    LinkedList<User> pullData() throws RemoteException;
    /**
     * Método que obtiene los datos de los usuarios
     * @return Lista de usuarios
     */
    boolean addUser(User user) throws RemoteException;
    /**
     * Método que obtiene los datos de los usuarios
     * @return Lista de usuarios
     */
    LinkedList<User> searchUser(String userToSearch);
    /**
     * Método que obtiene los datos de los usuarios
     * @return Lista de usuarios
     */
    boolean existUserByName(String userName);
    /**
     * Método que obtiene los datos de los usuarios
     * @return Lista de usuarios
     */
    boolean editUser(User oldUser, User editedUser) throws RemoteException;
    /**
     * Método que obtiene los datos de los usuarios
     * @return Lista de usuarios
     */
    int getEmployeeIdCounter();
    /**
     * Método que obtiene los datos de los usuarios
     */
    void setEmployeeIdCounter(int employeeIdCounter);
    /**
     * Método que obtiene los datos de los usuarios
     * @return Lista de usuarios
     */
    User[] userArray();
    /**
     * Método que obtiene los datos de los usuarios
     * @return Lista de usuarios
     */
    User[] userArray(LinkedList<User> userList);
}
