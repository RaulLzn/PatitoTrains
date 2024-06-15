package patitotrains.model.Managers;

import patitotrains.model.remote.client.RemoteServiceManager;
import patitotrains.model.remote.services.ServiceRemote;

import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class LoggerManager implements Remote {
    private static final String LOG_FILE = "Administator/src/main/java/patitotrains/temporal/UserTemp.txt";
    private final RemoteServiceManager serviceManager = RemoteServiceManager.getInstance();
    private final ServiceRemote serviceRemote = serviceManager.getServiceRemote();

    public LoggerManager() throws RemoteException, NotBoundException {
    }

    public boolean logUser(String user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, false))) {
            writer.print(user);
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de registro: " + e.getMessage());
            return false;
        }
    }

    public boolean clearLog() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE))) {
            writer.println("");
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de registro: " + e.getMessage());
            return false;
        }
    }

    public String readLog() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            StringBuilder log = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                log.append(line).append("\n");
            }
            return log.toString();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de registro: " + e.getMessage());
            return null;
        }
    }

    public void logAction(String action) {
        try {
            String userName = readLog();
            serviceRemote.logAction(action + ": " + userName);
        } catch (RemoteException e) {
            System.err.println("Error al realizar el registro de acción: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
