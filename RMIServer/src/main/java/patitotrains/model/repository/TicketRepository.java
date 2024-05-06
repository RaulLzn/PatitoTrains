package patitotrains.model.repository;

import patitotrains.model.domain.Passenger;
import patitotrains.model.domain.Route;
import patitotrains.model.domain.Ticket;
import patitotrains.model.repository.entity.TicketEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;

import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

import java.io.Serializable;

/**
 * Repositorio de tickets.
 */
public class TicketRepository implements Serializable {
    private static final String FILE_PATH = "Ticket.Json"; // Especifica la ruta correcta

    private final FileJsonAdapter<TicketEntity> jsonAdapter;

    public TicketRepository() {
        this.jsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Obtiene todos los tickets.
     *
     * @return Lista de tickets.
     */
    public List<Ticket> getAllTickets() {
        List<TicketEntity> ticketEntities = jsonAdapter.getObjects(FILE_PATH, TicketEntity[].class);
        List<Ticket> tickets = new LinkedList<>();

        Iterator<TicketEntity> iterator = ticketEntities.iterator();
        while (iterator.hasNext()) {
            tickets.add(mapToTicket(iterator.next()));
        }

        return tickets;
    }

    /**
     * Guarda un ticket en el archivo JSON.
     *
     * @param ticket Ticket a guardar.
     * @return Verdadero si el ticket se guardó correctamente, falso en caso contrario.
     */
    public boolean saveTicket(Ticket ticket) {
        List<TicketEntity> ticketEntities = jsonAdapter.getObjects(FILE_PATH, TicketEntity[].class);
        TicketEntity entity = mapToTicketEntity(ticket);
        ticketEntities.add(entity);
        return jsonAdapter.writeObjects(FILE_PATH, ticketEntities);
    }

    /**
     * Obtiene un ticket por su ID.
     *
     * @param id ID del ticket.
     * @return Ticket con el ID especificado, o nulo si no se encontró.
     */
    public Ticket getTicketById(String id) {
        List<TicketEntity> ticketEntities = jsonAdapter.getObjects(FILE_PATH, TicketEntity[].class);
        Iterator<TicketEntity> iterator = ticketEntities.iterator();
        while (iterator.hasNext()) {
            TicketEntity entity = iterator.next();
            if (entity.getId().equals(id)) {
                return mapToTicket(entity);
            }
        }
        return null;
    }

    /**
     * Mapea una entidad TicketEntity a un objeto Ticket.
     *
     * @param entity Entidad TicketEntity a mapear.
     * @return Objeto Ticket mapeado.
     */
    private Ticket mapToTicket(TicketEntity entity) {
        // Obtener los repositorios necesarios
        PassengerRepository passengerRepository = new PassengerRepository();
        RouteRepository routeRepository = new RouteRepository();

        Passenger passenger = null;
        String[] passengerIds = entity.getPassengerIds();
        if (passengerIds != null && passengerIds.length > 0) {
            passenger = passengerRepository.getPassengerById(passengerIds[0]);
        }

        Route route = null;
        String[] routeIds = entity.getRouteIds();
        if (routeIds != null && routeIds.length > 0) {
            route = routeRepository.getRouteById(routeIds[0]);
        }

        if (passenger != null && route != null) {
            return new Ticket(entity.getId(), passenger, entity.getValue(), entity.getSeatType(), route);
        } else {
            throw new RuntimeException("Failed to map TicketEntity to Ticket: Passenger or Route not found");
        }
    }

/**
     * Mapea un objeto Ticket a una entidad TicketEntity.
     *
     * @param ticket Objeto Ticket a mapear.
     * @return Entidad TicketEntity mapeada.
     */
    private TicketEntity mapToTicketEntity(Ticket ticket) {
        String passengerId = ticket.getPassenger().getId();
        String routeId = ticket.getRoute().getId();

        return new TicketEntity(ticket.getId(), new String[]{passengerId}, ticket.getValue(), ticket.getSeatType(), new String[]{routeId});
    }

    /**
     * Actualiza un ticket en el archivo JSON.
     *
     * @param ticket Ticket a actualizar.
     * @return Verdadero si el ticket se actualizó correctamente, falso en caso contrario.
     */
    public boolean updateTicket(Ticket ticket) {
        List<TicketEntity> ticketEntities = jsonAdapter.getObjects(FILE_PATH, TicketEntity[].class);
        TicketEntity entity = mapToTicketEntity(ticket);
        Iterator<TicketEntity> iterator = ticketEntities.iterator();
        while (iterator.hasNext()) {
            TicketEntity ticketEntity = iterator.next();
            if (ticketEntity.getId().equals(ticket.getId())) {
                ticketEntities.remove(ticketEntity);
                ticketEntities.add(entity);
                return jsonAdapter.writeObjects(FILE_PATH, ticketEntities);
            }
        }
        return false; // Ticket not found
    }

    /**
     * Elimina un ticket del archivo JSON.
     *
     * @param id ID del ticket a eliminar.
     * @return Verdadero si el ticket se eliminó correctamente, falso en caso contrario.
     */
    public boolean deleteTicket(String id) {
        List<TicketEntity> ticketEntities = jsonAdapter.getObjects(FILE_PATH, TicketEntity[].class);
        Iterator<TicketEntity> iterator = ticketEntities.iterator();
        while (iterator.hasNext()) {
            TicketEntity ticketEntity = iterator.next();
            if (ticketEntity.getId().equals(id)) {
                ticketEntities.remove(ticketEntity);
                return jsonAdapter.writeObjects(FILE_PATH, ticketEntities);
            }
        }

        return false; // Ticket not found
    }

    /**
     * Obtiene todos los tickets de un pasajero.
     *
     * @param passengerId ID del pasajero.
     * @return Lista de tickets del pasajero.
     */
    public List<Ticket> getTicketsByPassengerId(String passengerId) {
        List<TicketEntity> ticketEntities = jsonAdapter.getObjects(FILE_PATH, TicketEntity[].class);
        List<Ticket> tickets = new LinkedList<>();

        Iterator<TicketEntity> iterator = ticketEntities.iterator();
        while (iterator.hasNext()) {
            TicketEntity entity = iterator.next();
            if (entity.getPassengerIds()[0].equals(passengerId)) {
                tickets.add(mapToTicket(entity));
            }
        }

        return tickets;
    }

    /**
     * Elimina todos los tickets de un pasajero.
     *
     * @param passengerId ID del pasajero.
     * @return Verdadero si los tickets se eliminaron correctamente, falso en caso contrario.
     */
    public boolean deleteTicketsByPassengerId(String passengerId) {
        List<TicketEntity> ticketEntities = jsonAdapter.getObjects(FILE_PATH, TicketEntity[].class);
        Iterator<TicketEntity> iterator = ticketEntities.iterator();
        while (iterator.hasNext()) {
            TicketEntity ticketEntity = iterator.next();
            if (ticketEntity.getPassengerIds()[0].equals(passengerId)) {
                ticketEntities.remove(ticketEntity);
            }
        }

        return jsonAdapter.writeObjects(FILE_PATH, ticketEntities);
    }
}
