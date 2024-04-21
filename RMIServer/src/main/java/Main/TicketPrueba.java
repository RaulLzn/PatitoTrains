package Main;

import patitotrains.model.domain.ContactPerson;
import patitotrains.model.domain.Passenger;
import patitotrains.model.domain.Route;
import patitotrains.model.domain.Ticket;
import patitotrains.model.domain.types.IdType;
import patitotrains.model.domain.types.SeatType;
import patitotrains.model.repository.*;
import raul.Model.array.Array;

import java.time.LocalTime;

public class TicketPrueba {
    public static void main(String[] args) {
        // Crear un repositorio de pasajeros y una instancia de pasajero
        PassengerRepository passengerRepository = new PassengerRepository();
        Passenger passenger = new Passenger("1", "John Doe", new Array<>(new String[]{"12345678"}), "24", new IdType("124", "12345678"), "bucaramanga", new ContactPerson("12","Jane Doe", "Lazaro", new Array<>(new String[]{"87654321"})));

        // Guardar el pasajero
        passengerRepository.savePassenger(passenger);

        // Crear un repositorio de rutas y una instancia de ruta
        RouteRepository routeRepository = new RouteRepository();
        TrainRepository trainRepository = new TrainRepository();
        StationRepository stationRepository = new StationRepository();
        Route route = new Route("1", "Example Route", trainRepository.getTrainsByIds(new Array<>(new String[]{"T1"})), stationRepository.getStationsByIds(new Array<>(new String[]{"S1", "S2"})), LocalTime.now(), LocalTime.of(1,10), 100.0);

        // Guardar la ruta
        routeRepository.saveRoute(route);

        // Crear un repositorio de tickets
        TicketRepository ticketRepository = new TicketRepository();

        // Crear un ticket y guardarlo
        Ticket ticket = new Ticket("1", passenger, 50.0, new SeatType("12", "Standard", 12.2 ), route);
        ticketRepository.saveTicket(ticket);

//        // Obtener todos los tickets e imprimirlos
//        System.out.println("Todos los tickets:");
//        ticketRepository.getAllTickets().forEach(System.out::println);

//        // Actualizar el valor del ticket
//        ticket.setValue(60.0);
//        ticketRepository.updateTicket(ticket);

        // Obtener el ticket por ID e imprimirlo después de la actualización
        System.out.println("\nTicket actualizado:");
        System.out.println(ticketRepository.getTicketById("1"));

//        // Eliminar el ticket y obtener todos los tickets nuevamente para verificar
//        ticketRepository.deleteTicket("1");
//        System.out.println("\nTodos los tickets después de eliminar:");
//        ticketRepository.getAllTickets().forEach(System.out::println);
    }
}
