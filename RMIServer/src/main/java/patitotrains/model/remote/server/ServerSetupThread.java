package patitotrains.model.remote.server;

import patitotrains.model.remote.services.ServiceRemote;

import java.io.FileInputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

public class ServerSetupThread extends Thread {
    private final ServiceRemote adminService;

    public ServerSetupThread(ServiceRemote adminService) {
        this.adminService = adminService;
    }

    @Override
    public void run() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            int port = Integer.parseInt(properties.getProperty("rmi.port"));
            // Crea un registro RMI en el puerto especificado en el archivo de propiedades
            Registry registry = LocateRegistry.createRegistry(port);
            // Registra el servicio AdminServiceRemote en el registro RMI
            registry.bind("AdminService", adminService);
            System.out.println("Servidor RMI listo en el puerto " + port);
        } catch (Exception e) {
            System.err.println("Excepción en el servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}
