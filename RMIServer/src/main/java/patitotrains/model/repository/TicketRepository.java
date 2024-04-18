//package patitotrains.model.repository;
//
//import com.google.gson.annotations.JsonAdapter;
//import patitotrains.model.domain.Passenger;
//import patitotrains.model.domain.Route;
//import patitotrains.model.domain.Ticket;
//import patitotrains.model.domain.types.SeatType;
//import patitotrains.model.repository.PassengerRepository;
//import patitotrains.model.repository.RouteRepository;
//import patitotrains.model.repository.entity.TicketEntity;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class TicketRepository {
//
//    private JsonAdapter jsonAdapter;
//    private String filePath;
//
//    public TicketRepository(JsonAdapter jsonAdapter, String filePath) {
//        this.jsonAdapter = jsonAdapter;
//        this.filePath = filePath;
//    }
//
//    private Ticket mapToTicket(TicketEntity entity) {
//        // Implementar lógica de mapeo de TicketEntity a Ticket
//        PassengerRepository passengerRepository = new PassengerRepository();
//        SeatTypeRepository seatTypeRepository = new SeatTypeRepository();
//        RouteRepository routeRepository = new RouteRepository();
//
//        Passenger passenger = passengerRepository.findById(entity.getPassengerId());
//        SeatType seatType = seatTypeRepository.findById(entity.getSeatTypeId());
//        Route route = routeRepository.findById(entity.getRouteId());
//
//        return new Ticket(entity.getId(), passenger, entity.getValue(), seatType, route);
//    }
//
//    private TicketEntity mapToTicketEntity(Ticket ticket) {
//        // Implementar lógica de mapeo de Ticket a TicketEntity
//        return new TicketEntity(ticket.getId(), ticket.getPurchasDateTime(), ticket.getPassenger().getId(),
//                ticket.getValue(), ticket.getContactPerson().getId(), ticket.getSeatType().getId(),
//                ticket.getRoute().getId());
//    }
//
//    public Ticket findById(String id) {
//        // Implementar lógica para buscar un Ticket por su ID en la base de datos
//        List<TicketEntity> tickets = jsonAdapter.getObjects(filePath, TicketEntity[].class);
//        TicketEntity foundEntity = tickets.stream()
//                .filter(entity -> entity.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//        if (foundEntity != null) {
//            return mapToTicket(foundEntity);
//        }
//        return null;
//    }
//
//    public List<Ticket> findAll() {
//        // Implementar lógica para obtener todos los Tickets de la base de datos
//        List<TicketEntity> ticketEntities = jsonAdapter.getObjects(filePath, TicketEntity[].class);
//        return ticketEntities.stream().map(this::mapToTicket).collect(Collectors.toList());
//    }
//
//    public void save(Ticket ticket) {
//        // Implementar lógica para guardar un nuevo Ticket en la base de datos
//        List<TicketEntity> ticketEntities = jsonAdapter.getObjects(filePath, TicketEntity[].class);
//        TicketEntity ticketEntity = mapToTicketEntity(ticket);
//        ticketEntities.add(ticketEntity);
//        jsonAdapter.writeObjects(filePath, ticketEntities);
//    }
//
//    public void update(Ticket ticket) {
//        // Implementar lógica para actualizar un Ticket existente en la base de datos
//        List<TicketEntity> ticketEntities = jsonAdapter.getObjects(filePath, TicketEntity[].class);
//        TicketEntity updatedEntity = mapToTicketEntity(ticket);
//        for (int i = 0; i < ticketEntities.size(); i++) {
//            if (ticketEntities.get(i).getId().equals(ticket.getId())) {
//                ticketEntities.set(i, updatedEntity);
//                break;
//            }
//        }
//        jsonAdapter.writeObjects(filePath, ticketEntities);
//    }
//
//    public void delete(String id) {
//        // Implementar lógica para eliminar un Ticket de la base de datos
//        List<TicketEntity> ticketEntities = jsonAdapter.getObjects(filePath, TicketEntity[].class);
//        ticketEntities.removeIf(entity -> entity.getId().equals(id));
//        jsonAdapter.writeObjects(filePath, ticketEntities);
//    }
//}
