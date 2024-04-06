package upb.ticketmanagement.model.domain.interfaces;

import com.edu.upb.array.Array;
import com.edu.upb.linkedList.doubly.LinkedList;

import upb.ticketmanagement.model.domain.Lugagge;
import upb.ticketmanagement.model.domain.Ticket;
import upb.ticketmanagement.model.domain.Train;

public interface TicketManagerInterface {
    
    public  Array<Lugagge> getLugaggeFromTicket(Ticket ticket);

    public  String getWagonIdFromLugagge(Lugagge lugagge, Train train);

    public  Ticket getTicketById(String id);

    public  LinkedList<Ticket> searchTickets(String ticketToSearch);

    public  Ticket[] ticketArray();

    public  Ticket[] ticketArray( LinkedList<Ticket> ticketList);

    

}
