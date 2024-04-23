package patitotrains.model.domain.interfaces;


import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import patitotrains.model.domain.Luggage;
import patitotrains.model.domain.Ticket;
import patitotrains.model.domain.Train;

import java.rmi.RemoteException;

public interface TicketManagerInterface {
    public void pullData();
    
    public Array<Luggage> getLugaggeFromTicket(Ticket ticket) throws RemoteException;

    public  String getWagonIdFromLugagge(Luggage luggage, Train train);

    public LinkedList<Ticket> searchTickets(String ticketToSearch);

    public  Ticket[] ticketArray();

    public  Ticket[] ticketArray( LinkedList<Ticket> ticketList);

    

}
