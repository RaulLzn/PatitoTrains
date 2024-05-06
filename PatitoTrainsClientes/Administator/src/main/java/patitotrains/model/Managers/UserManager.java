package patitotrains.model.Managers;

import patitotrains.model.Managers.interfaceManagers.UserManagerInterface;
import patitotrains.model.domain.User;
import patitotrains.model.remote.client.RemoteServiceManager;
import patitotrains.model.remote.services.ServiceRemote;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class UserManager implements UserManagerInterface {

    private final RemoteServiceManager serviceManager = RemoteServiceManager.getInstance();
    private final ServiceRemote serviceRemote = serviceManager.getServiceRemote();


    private LinkedList<User> userList;

    private int employeeIdCounter;

    public UserManager() throws RemoteException, NotBoundException {
        userList = new LinkedList<>();
        pullData();

        employeeIdCounter = 100000+userList.size();

    }

    /**
     * Obtiene la lista de usuarios del servidor
     *
     * @return Lista de usuarios
     */
    @Override
    public LinkedList<User> pullData() {
        try {
            // Convierte la lista de usuarios a una lista de usuarios de la aplicación
            List<User> users = serviceRemote.getAllUsers();
            Iterator<User> iterator = users.iterator();
            while (iterator.hasNext()) {
                userList.add(iterator.next());
            }

            return userList;

        } catch (RemoteException e) {
            System.err.println("Error de comunicación con el servidor: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Agrega un usuario a la lista de usuarios
     *
     * @param user Usuario a agregar
     * @return true si se agrega correctamente, false en caso contrario
     */
    @Override
    public boolean addUser(User user){
        try {
            boolean addedLocally = userList.add(user);

            boolean addedRemotely = serviceRemote.saveUser(user);

            return addedLocally && addedRemotely;
        } catch (RemoteException e) {
            System.err.println("Error de comunicación con el servidor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public LinkedList<User> searchUser(String usertOSearch){
        int strLen = usertOSearch.length();
        LinkedList<User> toReturn = new LinkedList<>();
        User user;
        String uId;
        String uName; 
        String uLastName; 
        boolean finded;

        if (strLen < 4){
            return userList;
        }

        Iterator<User> iter = userList.iterator();
       

        while(iter.hasNext()){
            finded = false;
            user = iter.next();
            uId= user.getId();
            uName = user.getNames();
            uLastName = user.getLastNames();

            if(uName.length() >= strLen){

                if(uName.substring(0, strLen).equals(usertOSearch)){
                    finded = true;
                }
            }

            if(uLastName.length() >= strLen){

                if(uLastName.substring(0, strLen).equals(usertOSearch)){
                    finded = true;
                }
            }

            if(uId.length() >= strLen){

                if(uId.substring(0, strLen).equals(usertOSearch)){
                    finded = true;
                }
            }
            
            if(finded){
                toReturn.add(user);
            }
        }

        return toReturn;
    }

    @Override
    public boolean existUserByName(String userName){
        
        Iterator<User> iter = userList.iterator();

        while(iter.hasNext()){
            User user = iter.next();

            if(user.getUserName().equals(userName)){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean editUser(User oldRoute, User editedRoute){
        try {
            boolean editedLocally = userList.replace(oldRoute, editedRoute, t -> true);

            boolean editedRemotely = serviceRemote.updateUser(editedRoute);

            return editedLocally && editedRemotely;
        } catch (RemoteException e) {
            System.err.println("Error de comunicación con el servidor: " + e.getMessage());
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public int getEmployeeIdCounter() {
        return employeeIdCounter;
    }

    @Override
    public void setEmployeeIdCounter(int employeeIdCounter) {
        this.employeeIdCounter = employeeIdCounter;
    }

    @Override
    public User[] userArray(){
        User [] userArray = new User[userList.size()];
        int cnt = 0;
        Iterator<User> iter = userList.iterator();

        while(iter.hasNext()){
            userArray[cnt] = iter.next();
            cnt++;
        }
        return userArray;
    }

    @Override
    public User[] userArray( LinkedList<User> userList){
        User [] userArray = new User[userList.size()];
        int cnt = 0;
        Iterator<User> iter = userList.iterator();

        while(iter.hasNext()){
            userArray[cnt] = iter.next();
            cnt++;
        }
        return userArray;
    }



    
}
