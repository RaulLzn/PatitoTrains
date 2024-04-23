package patitotrains.model.remote.client;


import patitotrains.model.domain.Ticket;
import patitotrains.model.remote.services.ServiceRemote;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



public class AdminClient {
    public static void main(String[] args) {
        try {
            // Localiza el registro RMI en el servidor
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Obtiene una referencia al servicio remoto
            ServiceRemote adminService = (ServiceRemote) registry.lookup("AdminService");

            //imprimir la lista que devuelve getAllContainerWagons
            List<Ticket> ticketList = new LinkedList<Ticket>();
            ticketList = adminService.getAllTickets();

            Iterator<Ticket> iterator = ticketList.iterator();
            while (iterator.hasNext()) {
                Ticket ticket = iterator.next();
                System.out.println(ticket.toString());
            }

        } catch (Exception e) {
            System.err.println("Excepción en el cliente: " + e.toString());
            e.printStackTrace();
        }
    }
}
