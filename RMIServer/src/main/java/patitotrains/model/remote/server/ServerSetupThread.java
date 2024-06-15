package patitotrains.model.remote.server;

import patitotrains.model.remote.services.ServiceRemote;

import java.io.FileInputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;
/**
 * Clase que se encarga de manejar la conexión con el servidor RMI
 */
public class ServerSetupThread extends Thread {
    /**
     * Servicio remoto
     */
    private final ServiceRemote adminService;

    /**
     * Constructor de la clase
     * @param adminService Servicio remoto
     */
    public ServerSetupThread(ServiceRemote adminService) {
        this.adminService = adminService;
    }

    /**
     * Método que inicia el hilo
     */
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
