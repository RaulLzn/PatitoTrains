package patitotrains.model.remote.server;

import patitotrains.model.remote.services.ServiceImpl;
import patitotrains.model.remote.services.ServiceRemote;
import patitotrains.model.repository.*;

import java.rmi.RemoteException;

public class CentralizedServerAdmin {
    public static void main(String[] args) {
        try {
            // Crea un objeto AdminServiceImpl
            ContainerWagonRepository containerWagonRepository = new ContainerWagonRepository();
            PassengerWagonRepository passengerWagonRepository = new PassengerWagonRepository();
            ServiceRemote adminService = getServiceRemote(containerWagonRepository, passengerWagonRepository);

            // Inicia el hilo para configurar y registrar el servidor RMI
            ServerSetupThread setupThread = new ServerSetupThread(adminService);
            setupThread.start();
        } catch (Exception e) {
            System.err.println("Excepción en el servidor: " + e.toString());
            e.printStackTrace();
        }
    }

    private static ServiceRemote getServiceRemote(ContainerWagonRepository containerWagonRepository, PassengerWagonRepository passengerWagonRepository) throws RemoteException {
        RouteRepository routeRepository = new RouteRepository();
        UserRepository userRepository = new UserRepository();
        TrainRepository trainRepository = new TrainRepository();
        StationRepository stationRepository = new StationRepository();
        LuggageRepository luggageRepository = new LuggageRepository();
        PassengerRepository passengerRepository = new PassengerRepository();
        TicketRepository ticketRepository = new TicketRepository();


        ServiceRemote adminService = new ServiceImpl(containerWagonRepository, passengerWagonRepository, routeRepository, userRepository, trainRepository, stationRepository, luggageRepository, passengerRepository, ticketRepository);
        return adminService;
    }
}