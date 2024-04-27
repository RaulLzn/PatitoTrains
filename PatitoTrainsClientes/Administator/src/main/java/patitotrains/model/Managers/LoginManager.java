package patitotrains.model.Managers;

import patitotrains.model.remote.client.RemoteServiceManager;
import patitotrains.model.remote.services.ServiceRemote;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LoginManager {

    private static final String LOG_FILE = "Administator/src/main/java/patitotrains/temporal/UserTemp.txt";
    private final RemoteServiceManager serviceManager = RemoteServiceManager.getInstance();
    private final LoggerManager loggerManager = new LoggerManager();

    public LoginManager() throws NotBoundException, RemoteException {
        loggerManager.clearLog();
    }

    public boolean login(String userName, String password) {
        try {
            ServiceRemote serviceRemote = serviceManager.getServiceRemote();

            if (serviceRemote.verifyUser(userName, password)) {
                loggerManager.logUser(userName);
                loggerManager.logAction("Inicio de sesión exitoso para: " + userName);
                return true;
            } else {
                return false;
            }
        } catch (RemoteException | NotBoundException e) {
            System.err.println("Excepción en el inicio de sesión: " + e.toString());
            e.printStackTrace();
            return false;
        }
    }
}
