package patitotrains.model.remote.services;

import patitotrains.model.domain.*;
import patitotrains.model.repository.*;
import patitotrains.model.trazabilidad.ActionLogger;
import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.list.List;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Clase que implementa la interfaz ServiceRemote
 */
public class ServiceImpl extends UnicastRemoteObject implements ServiceRemote {

    private final ContainerWagonRepository containerWagonRepository;
    private final PassengerWagonRepository passengerWagonRepository;
    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final TrainRepository trainRepository;
    private final StationRepository stationRepository;
    private final LuggageRepository luggageRepository;
    private final PassengerRepository passengerRepository;
    private final TicketRepository ticketRepository;
    private final ActionLogger actionLogger;

    /**
     * Constructor de la clase
     * @param containerWagonRepository Repositorio de vagones contenedores
     * @param passengerWagonRepository Repositorio de vagones de pasajeros
     * @param routeRepository Repositorio de rutas
     * @param userRepository Repositorio de usuarios
     * @param trainRepository Repositorio de trenes
     * @param stationRepository Repositorio de estaciones
     * @param luggageRepository Repositorio de equipaje
     * @param passengerRepository Repositorio de pasajeros
     * @param ticketRepository Repositorio de boletos
     * @param actionLogger Logger de acciones
     * @throws RemoteException
     */
    public ServiceImpl(ContainerWagonRepository containerWagonRepository,
                       PassengerWagonRepository passengerWagonRepository,
                       RouteRepository routeRepository,
                       UserRepository userRepository,
                       TrainRepository trainRepository, StationRepository stationRepository, LuggageRepository luggageRepository, PassengerRepository passengerRepository, TicketRepository ticketRepository, ActionLogger actionLogger) throws RemoteException {
        super();
        this.containerWagonRepository = containerWagonRepository;
        this.passengerWagonRepository = passengerWagonRepository;
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.trainRepository = trainRepository;
        this.stationRepository = stationRepository;
        this.luggageRepository = luggageRepository;
        this.passengerRepository = passengerRepository;
        this.ticketRepository = ticketRepository;
        this.actionLogger = actionLogger;
    }

    /**
     * Método que obtiene todos los vagones contenedores
     * @return Lista de vagones contenedores
     * @throws RemoteException
     */
    @Override
    public List<ContainerWagon> getAllContainerWagons() throws RemoteException {
        return containerWagonRepository.getAllContainerWagons();
    }

    /**
     * Método que guarda un vagón contenedor
     * @param containerWagon Vagón contenedor
     * @return true si se guardó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean saveContainerWagon(ContainerWagon containerWagon) throws RemoteException {
        return containerWagonRepository.saveContainerWagon(containerWagon);
    }

    /**
     * Método que obtiene un vagón contenedor por ID
     * @param id ID del vagón contenedor
     * @return Vagón contenedor
     * @throws RemoteException
     */
    @Override
    public ContainerWagon getContainerWagonById(String id) throws RemoteException {
        return containerWagonRepository.getContainerWagonById(id);
    }

    /**
     * Método que elimina un vagón contenedor
     * @param id ID del vagón contenedor
     * @return true si se eliminó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean deleteContainerWagon(String id) throws RemoteException {
        return containerWagonRepository.deleteContainerWagon(id);
    }

    /**
     * Método que actualiza un vagón contenedor
     * @param containerWagon Vagón contenedor
     * @return true si se actualizó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean updateContainerWagon(ContainerWagon containerWagon) throws RemoteException {
        return containerWagonRepository.updateContainerWagon(containerWagon);
    }

    /**
     * Método que agrega equipaje a un vagón contenedor
     * @param containerWagonId ID del vagón contenedor
     * @param luggage Equipaje
     * @return true si se agregó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean addLuggageToContainerWagon(String containerWagonId, Luggage luggage) throws RemoteException {
        return containerWagonRepository.addLuggageToContainerWagon(containerWagonId, luggage);
    }

    /**
     * Método que elimina equipaje de un vagón contenedor
     * @param containerWagonId ID del vagón contenedor
     * @param luggageId ID del equipaje
     * @return true si se eliminó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean deleteLuggageFromContainerWagon(String containerWagonId, String luggageId) throws RemoteException {
        return containerWagonRepository.deleteLuggageFromContainerWagon(containerWagonId, luggageId);
    }

    /**
     * Método que obtiene todos los vagones de pasajeros
     * @return Lista de vagones de pasajeros
     * @throws RemoteException
     */
    @Override
    public List<PassengerWagon> getAllPassengerWagons() throws RemoteException {
        return passengerWagonRepository.getAllPassengerWagons();
    }

    /**
     * Método que guarda un vagón de pasajeros
     * @param passengerWagon Vagón de pasajeros
     * @return true si se guardó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean savePassengerWagon(PassengerWagon passengerWagon) throws RemoteException {
        return passengerWagonRepository.savePassengerWagon(passengerWagon);
    }

    /**
     * Método que actualiza un vagón de pasajeros
     * @param passengerWagon Vagón de pasajeros
     * @return true si se actualizó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean updatePassengerWagon(PassengerWagon passengerWagon) throws RemoteException {
        return passengerWagonRepository.updatePassengerWagon(passengerWagon);
    }

    /**
     * Método que elimina un vagón de pasajeros
     * @param id ID del vagón de pasajeros
     * @return true si se eliminó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean deletePassengerWagon(String id) throws RemoteException {
        return passengerWagonRepository.deletePassengerWagon(id);
    }

    /**
     * Método que obtiene un vagón de pasajeros por ID
     * @param id ID del vagón de pasajeros
     * @return Vagón de pasajeros
     * @throws RemoteException
     */
    @Override
    public PassengerWagon getPassengerWagonById(String id) throws RemoteException {
        return passengerWagonRepository.getPassengerWagonById(id);
    }

    /**
     * Método que obtiene varios vagones de pasajeros por ID
     * @param ids IDs de los vagones de pasajeros
     * @return Lista de vagones de pasajeros
     * @throws RemoteException
     */
    @Override
    public List<PassengerWagon> getPassengerWagonsByIds(Array<String> ids) throws RemoteException {
        return passengerWagonRepository.getPassengerWagonsByIds(ids);
    }

    @Override
    public List<Route> getAllRoutes() throws RemoteException {
        return routeRepository.getAllRoutes();
    }

    /**
     * Método que obtiene una ruta por ID
     * @param id ID de la ruta
     * @return Ruta
     * @throws RemoteException
     */
    @Override
    public Route getRouteById(String id) throws RemoteException {
        return routeRepository.getRouteById(id);
    }

    /**
     * Método que guarda una ruta
     * @param route Ruta
     * @return true si se guardó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean saveRoute(Route route) throws RemoteException {
        return routeRepository.saveRoute(route);
    }

    /**
     * Método que actualiza una ruta
     * @param route Ruta
     * @return true si se actualizó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean updateRoute(Route route) throws RemoteException {
        return routeRepository.updateRoute(route);
    }

    /**
     * Método que elimina una ruta
     * @param id ID de la ruta
     * @return true si se eliminó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean deleteRoute(String id) throws RemoteException {
        return routeRepository.deleteRoute(id);
    }

    /**
     * Método que deshabilita una ruta
     * @param id ID de la ruta
     * @return true si se deshabilitó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean disableRoute(String id) throws RemoteException {
        return routeRepository.disableRoute(id);
    }

    /**
     * Método que habilita una ruta
     * @param id ID de la ruta
     * @return true si se habilitó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean enableRoute(String id) throws RemoteException {
        return routeRepository.enableRoute(id);
    }

    /**
     * Método que obtiene todos los trenes
     * @return Lista de trenes
     * @throws RemoteException
     */
    @Override
    public Train getTrainById(String id) throws RemoteException {
        return trainRepository.getTrainById(id);
    }

    /**
     * Método que guarda un tren
     * @param train Tren
     * @return true si se guardó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean saveTrain(Train train) throws RemoteException {
        return trainRepository.saveTrain(train);
    }

    /**
     * Método que actualiza un tren
     * @param train Tren
     * @return true si se actualizó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean updateTrain(Train train) throws RemoteException {
        return trainRepository.updateTrain(train);
    }

    /**
     * Método que elimina un tren
     * @param id ID del tren
     * @return true si se eliminó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean deleteTrain(String id) throws RemoteException {
        return trainRepository.deleteTrain(id);
    }

    /**
     * Método que agrega un vagón de pasajeros a un tren
     * @param trainId ID del tren
     * @param passengerWagon Vagón de pasajeros
     * @return true si se agregó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean addPassengerWagonToTrain(String trainId, PassengerWagon passengerWagon) throws RemoteException {
        return trainRepository.addPassengerWagonToTrain(trainId, passengerWagon);
    }

    /**
     * Método que agrega un vagón contenedor a un tren
     * @param trainId ID del tren
     * @param containerWagon Vagón contenedor
     * @return true si se agregó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean addContainerWagonToTrain(String trainId, ContainerWagon containerWagon) throws RemoteException {
        return trainRepository.addContainerWagonToTrain(trainId, containerWagon);
    }

    /**
     * Método que obtiene todos los trenes
     * @return Lista de trenes
     * @throws RemoteException
     */
    @Override
    public List<Train> getAllTrains() throws RemoteException {
        return trainRepository.getAllTrains();
    }

    /**
     * Método que obtiene varios trenes por ID
     * @param ids IDs de los trenes
     * @return Lista de trenes
     * @throws RemoteException
     */
    @Override
    public LinkedList<Train> getTrainsByIds(Array<String> ids) throws RemoteException {
        return trainRepository.getTrainsByIds(ids);
    }

    /**
     * Método que obtiene todos los usuarios
     * @return Lista de usuarios
     * @throws RemoteException
     */
    @Override
    public List<User> getAllUsers() throws RemoteException {
        return userRepository.getAllUsers();
    }

    /**
     * Método que obtiene un usuario por ID
     * @param id ID del usuario
     * @return Usuario
     * @throws RemoteException
     */
    @Override
    public User getUserById(String id) throws RemoteException {
        return userRepository.getUserById(id);
    }

    /**
     * Método que guarda un usuario
     * @param user Usuario
     * @return true si se guardó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean saveUser(User user) throws RemoteException {
        return userRepository.saveUser(user);
    }

    /**
     * Método que actualiza un usuario
     * @param user Usuario
     * @return true si se actualizó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean updateUser(User user) throws RemoteException {
        return userRepository.updateUser(user);
    }

    /**
     * Método que elimina un usuario
     * @param id ID del usuario
     * @return true si se eliminó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean deleteUser(String id) throws RemoteException {
        return userRepository.deleteUser(id);
    }

    /**
     * Método que verifica un usuario
     * @param userName Nombre de usuario
     * @param password Contraseña
     * @return true si el usuario es válido, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean verifyUser(String userName, String password) throws RemoteException {
        return userRepository.verifyUser(userName, password);
    }

    /**
     * Método que obtiene todo el equipaje
     * @return Lista de equipaje
     * @throws RemoteException
     */
    @Override
    public List<Luggage> getAllLuggage() throws RemoteException {
        return luggageRepository.getAllLuggage();
    }

    /**
     * Método que guarda un equipaje
     * @param luggage Equipaje
     * @return true si se guardó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean saveLuggage(Luggage luggage) throws RemoteException {
        return luggageRepository.saveLuggage(luggage);
    }

    /**
     * Método que actualiza un equipaje
     * @param luggage Equipaje
     * @return true si se actualizó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean updateLuggage(Luggage luggage) throws RemoteException {
        return luggageRepository.updateLuggage(luggage);
    }

    /**
     * Método que elimina un equipaje
     * @param id ID del equipaje
     * @return true si se eliminó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean deleteLuggage(String id) throws RemoteException {
        return luggageRepository.deleteLuggage(id);
    }

    /**
     * Método que obtiene un equipaje por ID
     * @param id ID del equipaje
     * @return Equipaje
     * @throws RemoteException
     */
    @Override
    public Luggage getLuggageById(String id) throws RemoteException {
        return luggageRepository.getLuggageById(id);
    }

    /**
     * Método que obtiene todos los pasajeros
     * @return Lista de pasajeros
     * @throws RemoteException
     */
    @Override
    public List<Passenger> getAllPassengers() throws RemoteException {
        return passengerRepository.getAllPassengers();
    }

    /**
     * Método que guarda un pasajero
     * @param passenger Pasajero
     * @return true si se guardó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean savePassenger(Passenger passenger) throws RemoteException {
        return passengerRepository.savePassenger(passenger);
    }

    /**
     * Método que obtiene un pasajero por ID
     * @param id ID del pasajero
     * @return Pasajero
     * @throws RemoteException
     */
    @Override
    public Passenger getPassengerById(String id) throws RemoteException {
        return passengerRepository.getPassengerById(id);
    }

    /**
     * Método que elimina un pasajero
     * @param id ID del pasajero
     * @return true si se eliminó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean deletePassenger(String id) throws RemoteException {
        return passengerRepository.deletePassenger(id);
    }

    /**
     * Método que actualiza un pasajero
     * @param passenger Pasajero
     * @return true si se actualizó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean updatePassenger(Passenger passenger) throws RemoteException {
        return passengerRepository.updatePassenger(passenger);
    }

    /**
     * Método que obtiene una estación por ID
     * @param id ID de la estación
     * @return Estación
     * @throws RemoteException
     */
    @Override
    public Station getStationById(String id) throws RemoteException {
        return stationRepository.getStationById(id);
    }

    /**
     * Método que obtiene varias estaciones por ID
     * @param ids IDs de las estaciones
     * @return Lista de estaciones
     * @throws RemoteException
     */
    @Override
    public LinkedList<Station> getStationsByIds(Array<String> ids) throws RemoteException {
        return stationRepository.getStationsByIds(ids);
    }

    /**
     * Método que guarda una estación
     * @param station Estación
     * @return true si se guardó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean saveStation(Station station) throws RemoteException {
        return stationRepository.saveStation(station);
    }

    /**
     * Método que actualiza una estación
     * @param station Estación
     * @return true si se actualizó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean updateStation(Station station) throws RemoteException {
        return stationRepository.updateStation(station);
    }

    /**
     * Método que elimina una estación
     * @param id ID de la estación
     * @return true si se eliminó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean deleteStation(String id) throws RemoteException {
        return stationRepository.deleteStation(id);
    }

    /**
     * Método que obtiene todas las estaciones
     * @return Lista de estaciones
     * @throws RemoteException
     */
    @Override
    public List<Station> getAllStations() throws RemoteException {
        return stationRepository.getAllStations();
    }

    /**
     * Método que obtiene un boleto por ID
     * @param id ID del boleto
     * @return Boleto
     * @throws RemoteException
     */
    @Override
    public Ticket getTicketById(String id) throws RemoteException {
        return ticketRepository.getTicketById(id);
    }

    /**
     * Método que guarda un boleto
     * @param ticket Boleto
     * @return true si se guardó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean saveTicket(Ticket ticket) throws RemoteException {
        return ticketRepository.saveTicket(ticket);
    }

    /**
     * Método que actualiza un boleto
     * @param ticket Boleto
     * @return true si se actualizó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean updateTicket(Ticket ticket) throws RemoteException {
        return ticketRepository.updateTicket(ticket);
    }

    /**
     * Método que elimina un boleto
     * @param id ID del boleto
     * @return true si se eliminó correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean deleteTicket(String id) throws RemoteException {
        return ticketRepository.deleteTicket(id);
    }

    /**
     * Método que obtiene todos los boletos
     * @return Lista de boletos
     * @throws RemoteException
     */
    @Override
    public List<Ticket> getAllTickets() throws RemoteException {
        return ticketRepository.getAllTickets();
    }

    /**
     * Método que obtiene todos los boletos de un pasajero
     * @param passengerId ID del pasajero
     * @return Lista de boletos
     * @throws RemoteException
     */
    @Override
    public List<Ticket> getTicketsByPassengerId(String passengerId) throws RemoteException {
        return ticketRepository.getTicketsByPassengerId(passengerId);
    }

    /**
     * Método que elimina todos los boletos de un pasajero
     * @param passengerId ID del pasajero
     * @return true si se eliminaron correctamente, false en caso contrario
     * @throws RemoteException
     */
    @Override
    public boolean deleteTicketsByPassengerId(String passengerId) throws RemoteException {
        return ticketRepository.deleteTicketsByPassengerId(passengerId);
    }

    /**
     * Método que obtiene todos los boletos de un tren
     * @return Lista de boletos
     */
    @Override
    public boolean logAction(String action) {
        return actionLogger.logAction(action);
    }
}

