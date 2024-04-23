package patitotrains.model.domain;

import patitotrains.model.domain.interfaces.TicketManagerInterface;
import patitotrains.model.remote.services.ServiceRemote;
import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TicketManager implements TicketManagerInterface{

    private final Registry registry = LocateRegistry.getRegistry("localhost", 1099);

    private final ServiceRemote serviceRemote = (ServiceRemote) registry.lookup("AdminService");

    private LinkedList<Ticket> ticketList;

    public TicketManager() throws RemoteException, NotBoundException {
        ticketList = new LinkedList<>();
        pullData();
    }

    public void pullData() {
        try {
            ticketList = (LinkedList<Ticket>) serviceRemote.getAllTickets();
        } catch (RemoteException e) {
            System.err.println("Error de comunicación con el servidor: " + e.getMessage());
            e.printStackTrace();

        }
    }

    public  Array<Luggage> getLugaggeFromTicket(Ticket ticket) throws RemoteException {
        pullData();
        Array<Luggage> lugaggeArray = new Array<>(2);

        LinkedList<Luggage> luggageList = new LinkedList<>();
        luggageList = (LinkedList<Luggage>) serviceRemote.getAllLuggage();
        Iterator<Luggage> iterator = luggageList.iterator();
        while (iterator.hasNext()) {
            Luggage luggage = iterator.next();
            if (luggage.getTicketId().equals(ticket.getId())) {
                lugaggeArray.add(luggage);
            }

            if (lugaggeArray.size() == 2) {
                return lugaggeArray;
            }

        }
        return lugaggeArray;
    }

    public  String getWagonIdFromLugagge(Luggage luggage, Train train){
        pullData();
        Array<ContainerWagon> containerArray = train.getLuggageWagons();

        for(int ii = 0; ii < containerArray.size(); ii++){
            Iterator<Luggage> conIterator = containerArray.get(ii).getLuggages().iterator();

            while(conIterator.hasNext()){
                Luggage luggageCompar = conIterator.next();
                if(luggageCompar.getId().equals(luggage.getId())){
                    return containerArray.get(ii).getId();
                    
                }

            }
        }
        return "N/A";
    } 

    public LinkedList<Ticket> searchTickets(String ticketToSearch){
        pullData();
        int strLen = ticketToSearch.length();
        LinkedList<Ticket> toReturn = new LinkedList<>();
        Ticket ticket;
        String tId;
        String tName; 
        String tLastName; 
        boolean finded;

        if (strLen < 4){
            return new  LinkedList<>();
        }

        Iterator<Ticket> iter = ticketList.iterator();

        while(iter.hasNext()){
            finded = false;
            ticket = iter.next();
            tId= ticket.getId();
            tName = ticket.getPassenger().getNames();
            tLastName = ticket.getPassenger().getLastNames();

            if(tName.length() >= strLen){

                if(tName.substring(0, strLen).equals(ticketToSearch)){
                    finded = true;
                }
            }

            if(tLastName.length() >= strLen){

                if(tLastName.substring(0, strLen).equals(ticketToSearch)){
                    finded = true;
                }
            }

            if(tId.length() >= strLen){

                if(tId.substring(0, strLen).equals(ticketToSearch)){
                    finded = true;
                }
            }
            
            if(finded){
                toReturn.add(ticket);
            }
        }

        return toReturn;

    }   

    public Ticket[] ticketArray(){
        Ticket [] ticketArray = new Ticket[ticketList.size()];
        int cnt = 0;
        Iterator<Ticket> iter = ticketList.iterator();

        while(iter.hasNext()){
            ticketArray[cnt] = iter.next();
            cnt++;
        }
        return ticketArray;
    }


    public Ticket[] ticketArray( LinkedList<Ticket> ticketList){
        Ticket [] ticketArray = new Ticket[ticketList.size()];
        int cnt = 0;
        Iterator<Ticket> iter = ticketList.iterator();

        while(iter.hasNext()){
            ticketArray[cnt] = iter.next();
            cnt++;
        }
        return ticketArray;
    }

}
