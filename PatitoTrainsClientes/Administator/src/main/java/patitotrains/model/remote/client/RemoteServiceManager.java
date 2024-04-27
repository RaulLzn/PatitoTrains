package patitotrains.model.remote.client;

import patitotrains.model.remote.services.ServiceRemote;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

public class RemoteServiceManager {
    private static RemoteServiceManager instance;
    private String serverAddress;
    private int serverPort;

    private RemoteServiceManager() {
        loadConfig();
    }

    public static synchronized RemoteServiceManager getInstance() {
        if (instance == null) {
            instance = new RemoteServiceManager();
        }
        return instance;
    }

    public ServiceRemote getServiceRemote() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(serverAddress, serverPort);
        return (ServiceRemote) registry.lookup("AdminService");
    }

    private void loadConfig() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            serverAddress = properties.getProperty("rmi.host");
            serverPort = Integer.parseInt(properties.getProperty("rmi.port"));

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar la configuración: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
