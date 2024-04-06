package upb.ticketmanagement.model.domain;

import com.edu.upb.array.Array;
import com.edu.upb.linkedList.doubly.LinkedList;
import com.edu.upb.util.iterator.Iterator;

import upb.ticketmanagement.model.domain.interfaces.TicketManagerInterface;

public class TicketManager implements TicketManagerInterface{
    private LinkedList<Ticket> ticketList;

    public TicketManager(){
        ticketList = new LinkedList<>();
    }

    // Eleminar una vez se conecte con base de datos, hecho para simular el flujo
    public void addTicket(Ticket ticket){
        ticketList.add(ticket);
    }
    //---------------------------------------------------------------

    public  Array<Lugagge> getLugaggeFromTicket(Ticket ticket){
        Array<Lugagge> lugaggeArray = new Array<>(2);
        Train train = ticket.getRoute().getTrain();
        Array<ContainerWagon> containerArray = train.getLuggageWagons();

        for(int ii = 0; ii < containerArray.size(); ii++){
            Iterator<Lugagge> conIterator = containerArray.get(ii).getLuggages().iterator();

            while(conIterator.hasNext()){
                Lugagge lugagge = conIterator.next();
                if(lugagge.getTicketId().equals(ticket.getId())){
                    lugaggeArray.add(lugagge);
                    if(lugaggeArray.size() == 2){
                        return lugaggeArray;
                    }
                }

            }
        }
        
        return lugaggeArray;
    } 

    public  String getWagonIdFromLugagge(Lugagge lugagge, Train train){

        Array<ContainerWagon> containerArray = train.getLuggageWagons();

        for(int ii = 0; ii < containerArray.size(); ii++){
            Iterator<Lugagge> conIterator = containerArray.get(ii).getLuggages().iterator();

            while(conIterator.hasNext()){
                Lugagge lugaggeCompar = conIterator.next();
                if(lugaggeCompar.getId().equals(lugagge.getId())){
                    return containerArray.get(ii).getId();
                    
                }

            }
        }
        return "N/A";
    } 

    
    public Ticket getTicketById(String id){

        Iterator<Ticket> iter = ticketList.iterator();

        while(iter.hasNext()){
            Ticket ticket = iter.next();

            if(ticket.getId().equals(id)){
                return ticket;
            }
        }

        return null;

    }
    public LinkedList<Ticket> searchTickets(String ticketToSearch){
        int strLen = ticketToSearch.length();
        LinkedList<Ticket> toReturn = new LinkedList<>();
        Ticket ticket;
        String tId;
        String tName; 
        String tLastName; 
        boolean finded;

        if (strLen < 4){
            return ticketList;
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
