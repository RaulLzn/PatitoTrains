package upb.ticketmanagement.model.domain;

import java.time.LocalTime;

import com.edu.upb.array.Array;
import com.edu.upb.linkedList.doubly.LinkedList;
import com.edu.upb.util.iterator.Iterator;

import upb.ticketmanagement.model.domain.interfaces.TicketManagerInterface;
import upb.ticketmanagement.model.domain.types.IdType;
import upb.ticketmanagement.model.domain.types.SeatType;
import upb.ticketmanagement.model.domain.types.TrainType;

public class TicketManager implements TicketManagerInterface{
    private LinkedList<Ticket> ticketList;

    public TicketManager(){
        ticketList = new LinkedList<>();
        pullData();
    }

    public void pullData(){
        SeatType premium = new SeatType("001", "Premium", 1800);
        SeatType executive = new SeatType("002", "Ejecutivo", 1200);
        SeatType standard = new SeatType("003", "Estandar", 1000);

        IdType ti = new IdType("001", "T.I");
        IdType cc = new IdType("002", "C.C");

        TrainType arnold = new TrainType("001", "Arnold", 32);
        TrainType mercedes = new TrainType("002", "Mercedes", 28);

        

        Train train1 = new Train("06530", "Tren Amarillo", arnold, 10, 5);
        Train train2 = new Train("06545", "Tren Azul", mercedes, 8, 4);
        Train train3 = new Train("09378", "Tren Rojo", arnold, 12, 6);

        LinkedList<Train> trains1 = new LinkedList<>();
        trains1.add(train1);

        LinkedList<Train> trains2 = new LinkedList<>();
        trains2.add(train2);


        LinkedList<Train> trains3 = new LinkedList<>();
        trains3.add(train3);



        Lugagge lugagge1 = new Lugagge("0003", 60, "1a00000000000000000000000000000");
        Lugagge lugagge2 = new Lugagge("0002", 68, "1a00000000000000000000000000000");

        train1.getLuggageWagons().add(new ContainerWagon("138"));
        train1.getLuggageWagons().get(0).getLuggages().add(lugagge1);
        train1.getLuggageWagons().get(0).getLuggages().add(lugagge2);

        Station station1 = new Station("001", "Estacion Triangulo");
        Station station2 = new Station("002", "Estacion Cuadrado");
        Station station3 = new Station("003", "Estacion Circulo");
        Station station4 = new Station("004", "Estacion Pentagono");

        LinkedList<Station> stations1 = new LinkedList<>();
        stations1.add(station1);
        stations1.add(station2);

        LinkedList<Station> stations2 = new LinkedList<>();
        stations2.add(station1);
        stations2.add(station2);
        stations2.add(station3);
        stations2.add(station4);
        
        

        Route route1 = new Route("10001","Ruta del Sol", trains1, stations1,
                 LocalTime.of(10, 10), LocalTime.of(12, 10), 100);
        Route route2 = new Route("10002","Ruta de luna", trains2, stations2,
                 LocalTime.of(9, 10), LocalTime.of(10, 10), 100);
        Route route3 = new Route("10003","Ruta del god", trains3, stations2,
                 LocalTime.of(18, 10), LocalTime.of(20, 10), 100);

        Array<String> numbers = new Array<>(5);
        numbers.add("300102301");
        numbers.add("386102301");
        numbers.add("326302301");

        Array<String> numbers2 = new Array<>(5);
        numbers2.add("300912389");
        numbers2.add("300128912");
        numbers2.add("300012310");

        ticketList.add(new Ticket("1000000000000000000000000000001",
        new Passenger("John", "Doe", numbers, "123456789", cc, "123 Main St",
                new ContactPerson("Jane", "Doe", numbers2)),
        75000, standard, route1));

        ticketList.add(new Ticket("1000000000000000000000000000002",
        new Passenger("Alice", "Smith", numbers, "987654321", ti, "456 Elm St",
                new ContactPerson("Bob", "Smith", numbers)),
        90000, executive, route2));

        ticketList.add(new Ticket("1000000000000000000000000000003",
        new Passenger("Emily", "Johnson", numbers, "456789123", cc, "789 Oak St",
                new ContactPerson("Jack", "Johnson", numbers)),
        110000, premium, route3));

        ticketList.add(new Ticket("1000000000000000000000000000004",
        new Passenger("Michael", "Williams", numbers, "654321987", cc, "987 Maple St",
                new ContactPerson("Olivia", "Williams", numbers)),
        80000, standard, route1));

        ticketList.add(new Ticket("1000000000000000000000000000005",
        new Passenger("Sophia", "Brown", numbers, "789123456", ti, "321 Pine St",
                new ContactPerson("William", "Brown", numbers)),
        95000, executive, route2));

        ticketList.add(new Ticket("1000000000000000000000000000006",
        new Passenger("Ethan", "Jones", numbers, "321654987", cc, "654 Cedar St",
                new ContactPerson("Ava", "Jones", numbers)),
        120000, premium, route3));

        ticketList.add(new Ticket("1000000000000000000000000000007",
        new Passenger("Isabella", "Davis", numbers, "852963741", cc, "753 Birch St",
                new ContactPerson("James", "Davis", numbers)),
        85000, standard, route1));

        ticketList.add(new Ticket("1000000000000000000000000000008",
        new Passenger("Noah", "Miller", numbers, "369852147", ti, "147 Walnut St",
                new ContactPerson("Emma", "Miller", numbers)),
        100000, executive, route2));

        ticketList.add(new Ticket("1000000000000000000000000000009",
        new Passenger("Oliver", "Wilson", numbers, "159753456", cc, "369 Chestnut St",
                new ContactPerson("Charlotte", "Wilson", numbers)),
        115000, premium, route3));

        ticketList.add(new Ticket("1000000000000000000000000000010",
        new Passenger("Liam", "Taylor", numbers, "753159456", cc, "852 Oakwood St",
                new ContactPerson("Amelia", "Taylor", numbers)),
        88000, standard, route1));

        ticketList.add(new Ticket("1000000000000000000000000000011",
        new Passenger("Emma", "Anderson", numbers, "987654321", ti, "456 Elm St",
                new ContactPerson("John", "Anderson", numbers)),
        90000, executive, route2));

ticketList.add(new Ticket("1000000000000000000000000000012",
        new Passenger("James", "Brown", numbers, "456789123", cc, "789 Oak St",
                new ContactPerson("Sophia", "Brown", numbers)),
        110000, premium, route3));

ticketList.add(new Ticket("1000000000000000000000000000013",
        new Passenger("Olivia", "Clark", numbers, "654321987", cc, "987 Maple St",
                new ContactPerson("Michael", "Clark", numbers)),
        80000, standard, route1));

ticketList.add(new Ticket("1000000000000000000000000000014",
        new Passenger("William", "Davis", numbers, "789123456", ti, "321 Pine St",
                new ContactPerson("Isabella", "Davis", numbers)),
        95000, executive, route2));

ticketList.add(new Ticket("1000000000000000000000000000015",
        new Passenger("Ava", "Evans", numbers, "321654987", cc, "654 Cedar St",
                new ContactPerson("Ethan", "Evans", numbers)),
        120000, premium, route3));

ticketList.add(new Ticket("1000000000000000000000000000016",
        new Passenger("Charlotte", "Fisher", numbers, "852963741", cc, "753 Birch St",
                new ContactPerson("Oliver", "Fisher", numbers)),
        85000, standard, route1));

ticketList.add(new Ticket("1000000000000000000000000000017",
        new Passenger("Daniel", "Garcia", numbers, "369852147", ti, "147 Walnut St",
                new ContactPerson("Emily", "Garcia", numbers)),
        100000, executive, route2));

ticketList.add(new Ticket("1000000000000000000000000000018",
        new Passenger("Hannah", "Harris", numbers, "159753456", cc, "369 Chestnut St",
                new ContactPerson("Noah", "Harris", numbers)),
        115000, premium, route3));

ticketList.add(new Ticket("1000000000000000000000000000019",
        new Passenger("Ian", "Irwin", numbers, "753159456", cc, "852 Oakwood St",
                new ContactPerson("Olivia", "Irwin", numbers)),
        88000, standard, route1));

ticketList.add(new Ticket("1000000000000000000000000000020",
        new Passenger("Julia", "Johnson", numbers, "123456789", cc, "123 Main St",
                new ContactPerson("Alexander", "Johnson", numbers)),
        75000, standard, route1));

        ticketList.add(new Ticket("1000000000000000000000000000021",
        new Passenger("Jimmy", "Montana", numbers, "123456789", cc, "123 Main St",
                new ContactPerson("Alexander", "Johnson", numbers)),
        75000, standard, route1));


        ticketList.add(new Ticket("100000000000000000000000000022",
        new Passenger("Charizard", "Flame", numbers, "456789123", cc, "456 Oak St",
                new ContactPerson("Charmeleon", "Flame", numbers)),
        110000, premium, route3));

ticketList.add(new Ticket("100000000000000000000000000023",
        new Passenger("Squirtle", "Turtle", numbers, "654321987", cc, "789 Maple St",
                new ContactPerson("Blastoise", "Turtle", numbers)),
        80000, standard, route1));

ticketList.add(new Ticket("100000000000000000000000000024",
        new Passenger("Bulbasaur", "Seed", numbers, "789123456", ti, "321 Pine St",
                new ContactPerson("Ivysaur", "Seed", numbers)),
        95000, executive, route2));

ticketList.add(new Ticket("100000000000000000000000000025",
        new Passenger("Mewtwo", "Psychic", numbers, "321654987", cc, "654 Cedar St",
                new ContactPerson("Mew", "Psychic", numbers)),
        120000, premium, route3));

ticketList.add(new Ticket("100000000000000000000000000026",
        new Passenger("Snorlax", "Sleepy", numbers, "852963741", cc, "753 Birch St",
                new ContactPerson("Jigglypuff", "Sleepy", numbers)),
        85000, standard, route1));

ticketList.add(new Ticket("100000000000000000000000000027",
        new Passenger("Gyarados", "Dragon", numbers, "369852147", ti, "147 Walnut St",
                new ContactPerson("Magikarp", "Dragon", numbers)),
        100000, executive, route2));

ticketList.add(new Ticket("100000000000000000000000000028",
        new Passenger("Eevee", "Evolve", numbers, "159753456", cc, "369 Chestnut St",
                new ContactPerson("Vaporeon", "Evolve", numbers)),
        115000, premium, route3));

ticketList.add(new Ticket("100000000000000000000000000029",
        new Passenger("Dragonite", "Dragon", numbers, "753159456", cc, "852 Oakwood St",
                new ContactPerson("Dratini", "Dragon", numbers)),
        88000, standard, route1));

ticketList.add(new Ticket("100000000000000000000000000030",
        new Passenger("Gengar", "Ghost", numbers, "123456789", cc, "123 Main St",
                new ContactPerson("Haunter", "Ghost", numbers)),
        75000, standard, route1));
    }
    public  Array<Lugagge> getLugaggeFromTicket(Ticket ticket){
        Array<Lugagge> lugaggeArray = new Array<>(2);
        Train train = ticket.getRoute().getTrains().peek();
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

    public LinkedList<Ticket> searchTickets(String ticketToSearch){
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
