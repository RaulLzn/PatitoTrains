package patitotrains.model.domain;

import patitotrains.model.remote.services.ServiceRemote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LoginManager {
    
    public LoginManager(){

    }

    public boolean login(String user, String password){
        try {
            // Localiza el registro RMI en el servidor
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Obtiene una referencia al servicio remoto de usuarios
            ServiceRemote serviceRemote = (ServiceRemote) registry.lookup("AdminService");

            // Llama al método remoto para verificar el inicio de sesión
            return serviceRemote.verifyUser(user, password);
        } catch (Exception e) {
            System.err.println("Excepción en el inicio de sesión: " + e.toString());
            e.printStackTrace();
            return false;
        }
    }

}
