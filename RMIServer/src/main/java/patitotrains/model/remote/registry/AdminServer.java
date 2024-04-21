package patitotrains.model.remote.registry;

import patitotrains.model.remote.services.AdminServiceImpl;
import patitotrains.model.remote.services.servicesRemote.AdminServiceRemote;
import patitotrains.model.repository.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AdminServer {
    public static void main(String[] args) {
        try {
            // Crea un objeto AdminServiceImpl
            ContainerWagonRepository containerWagonRepository = new ContainerWagonRepository();
            PassengerWagonRepository passengerWagonRepository = new PassengerWagonRepository();
            RouteRepository routeRepository = new RouteRepository();
            UserRepository userRepository = new UserRepository();
            TrainRepository trainRepository = new TrainRepository();

            AdminServiceRemote adminService = new AdminServiceImpl(containerWagonRepository, passengerWagonRepository, routeRepository, userRepository, trainRepository);

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
