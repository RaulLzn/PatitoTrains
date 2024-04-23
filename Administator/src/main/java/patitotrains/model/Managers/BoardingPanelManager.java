package patitotrains.model.Managers;

import patitotrains.model.domain.Ticket;
import patitotrains.model.remote.services.ServiceRemote;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.priorityqueue.PriorityQueue;
import raul.Model.util.Iterator.Iterator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BoardingPanelManager {

    private final Registry registry = LocateRegistry.getRegistry("localhost", 1099);
    private final ServiceRemote serviceRemote = (ServiceRemote) registry.lookup("AdminService");


    PriorityQueue<Ticket> ticketsQueue;

    public BoardingPanelManager() throws RemoteException, NotBoundException {
        ticketsQueue = new PriorityQueue<>(3);
        pullData();
    }

    public void pullData() {
        try {
            ticketsQueue = new PriorityQueue<>(3);
            LinkedList<Ticket> ticketList = (LinkedList<Ticket>) serviceRemote.getAllTickets();

            //

            if (ticketList != null) {
                if (!ticketList.isEmpty()) {
                    setDataInPriority(ticketList);
                } else {
                    System.out.println("La lista de tickets está vacía.");
                }
            } else {
                System.err.println("Error: La lista de tickets obtenida del servidor remoto es nula.");
            }
        } catch (RemoteException e) {
            System.err.println("Error de comunicación con el servidor: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Error al procesar los datos de los tickets: " + ex.getMessage());
            ex.printStackTrace();
        }
    }




    public Ticket[] getTicketArrayOrdered(){
        Ticket[] tickets = new Ticket[ticketsQueue.size()];
        int cnt = 0;
        while(!ticketsQueue.isEmpty()){
            tickets[cnt] =ticketsQueue.extract();
            cnt++;
        }
        return tickets;


    }

    private void setDataInPriority(LinkedList<Ticket> list){
        Iterator<Ticket> iter =  list.iterator();

        while(iter.hasNext()){
            Ticket ticket = iter.next();
            ticketsQueue.Insert(getPriorityBySeat(ticket), ticket);
        }

    }

    private int getPriorityBySeat(Ticket ticket){
        if(ticket.getSeatType().getDescription() == "Premium"){
            return 0;
        }
        if(ticket.getSeatType().getDescription() == "Ejecutivo"){
            return 1;
        }
        if(ticket.getSeatType().getDescription() == "Estandar"){
            return 2;
        }
        return 2;
    }
    
    
}
