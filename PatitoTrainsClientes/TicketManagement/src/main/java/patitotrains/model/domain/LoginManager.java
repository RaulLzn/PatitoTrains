package patitotrains.model.domain;

import patitotrains.model.remote.client.RemoteServiceManager;
import patitotrains.model.remote.services.ServiceRemote;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LoginManager {
    private final RemoteServiceManager remoteServiceManager;

    public LoginManager() {
        remoteServiceManager = RemoteServiceManager.getInstance();
    }

    public boolean login(String user, String password) {
        try {
            // Obtiene una referencia al servicio remoto utilizando RemoteServiceManager
            ServiceRemote serviceRemote = remoteServiceManager.getServiceRemote();

            // Registra el inicio de sesión en el servicio remoto
            serviceRemote.logAction("Inicio de sesión en Ticket Manager: " + user);

            // Llama al método remoto para verificar el inicio de sesión
            return serviceRemote.verifyUser(user, password);

        } catch (RemoteException | NotBoundException e) {
            System.err.println("Excepción en el inicio de sesión: " + e.toString());
            e.printStackTrace();
            return false;
        }
    }
}
