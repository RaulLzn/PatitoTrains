package patitotrains.model.remote.server;

import patitotrains.model.remote.interfaces.*;
import patitotrains.model.remote.services.AdminServiceImpl;
import patitotrains.model.remote.services.servicesRemote.AdminServiceRemote;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CentralizedServerAdmin {
    public static void main(String[] args) {
        try {
            // Crear un registro RMI en el puerto 1099 (puerto predeterminado)
            Registry registry = LocateRegistry.createRegistry(1099);

            // Crear instancias de los servicios remotos
            PassengerWagonServiceRemote passengerWagonService = new PassengerWagonServiceImpl();
            ContainerWagonServiceRemote containerWagonService = new ContainerWagonServiceImpl();
            RouteServiceRemote routeService = new RouteServiceImpl();
            TrainServiceRemote trainService = new TrainServiceImpl();
            UserServiceRemote userService = new UserServiceImpl();

            // Crear una instancia del servicio remoto AdminService con las implementaciones de los servicios
            AdminServiceRemote adminService = new AdminServiceImpl(passengerWagonService, containerWagonService,
                    routeService, trainService, userService);

            // Registrar el servicio remoto en el registro RMI con un nombre único
            registry.rebind("AdminService", adminService);

            System.out.println("Servicio remoto registrado en el registro RMI.");
        } catch (RemoteException e) {
            System.err.println("Error al registrar el servicio remoto: " + e.getMessage());
        }
    }
}
