package patitotrains.model.remote.server;

import patitotrains.model.remote.services.ServiceRemote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerSetupThread extends Thread {
    private final ServiceRemote adminService;

    public ServerSetupThread(ServiceRemote adminService) {
        this.adminService = adminService;
    }

    @Override
    public void run() {
        try {
            // Crea un registro RMI en el puerto 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Registra el servicio AdminServiceRemote en el registro RMI
            registry.bind("AdminService", adminService);

            System.out.println("Servidor RMI listo.");
        } catch (Exception e) {
            System.err.println("Excepción en el servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}
