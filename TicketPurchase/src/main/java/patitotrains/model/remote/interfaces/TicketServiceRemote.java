package patitotrains.model.remote.interfaces;

import patitotrains.model.domain.Ticket;
import raul.Model.util.list.List;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicketServiceRemote extends Remote {

    Ticket getTicketById(String id) throws RemoteException;
    boolean saveTicket(Ticket ticket) throws RemoteException;
    boolean updateTicket(Ticket ticket) throws RemoteException;
    boolean deleteTicket(String id) throws RemoteException;
    List<Ticket> getAllTickets() throws RemoteException;
    List<Ticket> getTicketsByPassengerId(String passengerId) throws RemoteException;
    boolean deleteTicketsByPassengerId(String passengerId) throws RemoteException;
}
