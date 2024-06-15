package patitotrains.model.remote.server;

import patitotrains.model.remote.services.ServiceImpl;
import patitotrains.model.remote.services.ServiceRemote;
import patitotrains.model.repository.*;
import patitotrains.model.trazabilidad.ActionLogger;

import java.rmi.RemoteException;
/**
 * Clase que se encarga de iniciar el servidor centralizado
 */
public class CentralizedServerAdmin {
    /**
     * Método principal
     * @param args Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        try {
            /**
             * Se crea un objeto de tipo ServiceRemote
             */
            ServiceRemote adminService = getServiceRemote();

            /**
             * Se crea un hilo para configurar el servidor
             */
            ServerSetupThread setupThread = new ServerSetupThread(adminService);
            setupThread.start();
        } catch (Exception e) {
            System.err.println("Excepción en el servidor: " + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * Método que obtiene el servicio remoto
     * @return Servicio remoto
     * @throws RemoteException
     */
    private static ServiceRemote getServiceRemote() throws RemoteException {
        RouteRepository routeRepository = new RouteRepository();
        UserRepository userRepository = new UserRepository();
        TrainRepository trainRepository = new TrainRepository();
        StationRepository stationRepository = new StationRepository();
        LuggageRepository luggageRepository = new LuggageRepository();
        PassengerRepository passengerRepository = new PassengerRepository();
        TicketRepository ticketRepository = new TicketRepository();
        ContainerWagonRepository containerWagonRepository = new ContainerWagonRepository();
        PassengerWagonRepository passengerWagonRepository = new PassengerWagonRepository();
        ActionLogger actionLogger = new ActionLogger();


        /**
         * Se crea un objeto de tipo ServiceImpl
         */
        return new ServiceImpl(containerWagonRepository, passengerWagonRepository, routeRepository, userRepository, trainRepository, stationRepository, luggageRepository, passengerRepository, ticketRepository, actionLogger);
    }
}