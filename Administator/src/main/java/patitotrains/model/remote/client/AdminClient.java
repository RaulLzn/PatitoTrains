package patitotrains.model.remote.client;

import patitotrains.model.remote.services.ServiceRemote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



public class AdminClient {
    public static void main(String[] args) {
        try {
            // Localiza el registro RMI en el servidor
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Obtiene una referencia al servicio remoto
            ServiceRemote adminService = (ServiceRemote) registry.lookup("AdminService");

            //imprimir la lista que devuelve getAllRoutes
            System.out.println(adminService.getAllRoutes());


        } catch (Exception e) {
            System.err.println("Excepción en el cliente: " + e.toString());
            e.printStackTrace();
        }
    }
}
