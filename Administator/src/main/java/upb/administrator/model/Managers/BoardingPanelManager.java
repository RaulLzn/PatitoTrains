package upb.administrator.model.Managers;

import com.edu.upb.linkedList.doubly.LinkedList;
import com.edu.upb.priorityQueue.PriorityQueue;
import com.edu.upb.util.iterator.Iterator;

import upb.administrator.model.domain.Passenger;
import upb.administrator.model.domain.Route;
import upb.administrator.model.domain.Ticket;
import upb.administrator.model.domain.Train;
import upb.administrator.model.domain.types.SeatType;

public class BoardingPanelManager {

    PriorityQueue<Ticket> ticketsQueue;

    public BoardingPanelManager(){
        ticketsQueue = new PriorityQueue<>(3);
    }

    public void pullData(){ 
        LinkedList<Ticket> ticketList = new LinkedList<>(); // baja los datos de los json y los guarda en una lista

        //Simular
        Train train =  new Train("100001", "Super trensito", null, 0, 0);
        LinkedList<Train> trains = new LinkedList<>();
        trains.add(train);
        Route route = new Route("1001","Super Ruta", trains, null, null, null, 0);
        SeatType seatType1 = new SeatType("001", "Estandar", 0);
        SeatType seatType2 = new SeatType("002", "Ejecutivo", 0);
        SeatType seatType3 = new SeatType("003", "Premium", 0);
        
        
        ticketList.add(new Ticket("100000000001", new Passenger("Juan", "Granados", null, null, null, null, null), 0, seatType1, route));
        ticketList.add(new Ticket("100000000002", new Passenger("María", "López", null, null, null, null, null), 0, seatType2, route));
        ticketList.add(new Ticket("100000000003", new Passenger("Carlos", "Martínez", null, null, null, null, null), 0, seatType3, route));
        ticketList.add(new Ticket("100000000004", new Passenger("Ana", "González", null, null, null, null, null), 0, seatType1, route));
        ticketList.add(new Ticket("100000000005", new Passenger("Pedro", "Sánchez", null, null, null, null, null), 0, seatType2, route));
        ticketList.add(new Ticket("100000000006", new Passenger("Laura", "Díaz", null, null, null, null, null), 0, seatType3, route));
        ticketList.add(new Ticket("100000000007", new Passenger("Diego", "Rodríguez", null, null, null, null, null), 0, seatType1, route));
        ticketList.add(new Ticket("100000000008", new Passenger("Sofía", "Fernández", null, null, null, null, null), 0, seatType2, route));
        ticketList.add(new Ticket("100000000009", new Passenger("Manuel", "Pérez", null, null, null, null, null), 0, seatType3, route));
        ticketList.add(new Ticket("100000000010", new Passenger("Elena", "Muñoz", null, null, null, null, null), 0, seatType1, route));
        




        setDataInPriority(ticketList);

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
            ticketsQueue.insert(getPriorityBySeat(ticket), ticket);
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
