package upb.ticketpurchase.model.manager;

import com.edu.upb.array.Array;
import com.edu.upb.linkedList.doubly.LinkedList;
import com.edu.upb.nolineal.graph.weightedGraph.Graph;
import com.edu.upb.util.iterator.Iterator;
import java.time.*;

import upb.ticketpurchase.model.domain.Route;
import upb.ticketpurchase.model.domain.Station;
import upb.ticketpurchase.model.domain.Train;
import upb.ticketpurchase.model.domain.types.IdType;
import upb.ticketpurchase.model.domain.types.SeatType;


public class PurchaseManager {

    private LinkedList<IdType> idTypes;
    private LinkedList<Station> stations;
    private LinkedList<Route> routes;
    private LinkedList<SeatType> seatTypes;
    private Graph<Station> map;
    private  Graph<Station> personalMap;
    private Array<Array<String>> trainNameEdgePersonalMap; 

    public PurchaseManager(){
        idTypes = new LinkedList<>();
        stations = new LinkedList<>();
        routes = new LinkedList<>();
        seatTypes = new LinkedList<>();
        map = new Graph<>(11);
        personalMap = new Graph<>(11);
        trainNameEdgePersonalMap = new Array<>(11);

        for(int ii = 0; ii < trainNameEdgePersonalMap.lenght(); ii++){
            trainNameEdgePersonalMap.set(ii, new Array<>(11));
        }
        fillMap();
    }

    public String ticketId(){
        return "10000001";
    }

    public Array<Station> getPersonalPath(Station departure, Station arrival){
        return personalMap.ShortestPath(departure, arrival);
    }

    public void pullSeatTypes(){
        seatTypes.add(new SeatType("1001", "Estandar", 1000));
        seatTypes.add(new SeatType("1002", "Ejecutivo", 1200));
        seatTypes.add(new SeatType("1003", "Premium", 1800));
    }
    public void pullIdTypes(){
        IdType id1 = new IdType("001", "Tarjeta de Identidad");
        IdType id2 = new IdType("002", "Cedula de Ciudadania");
        idTypes.add(id1);
        idTypes.add(id2);

    }

    public String[] getNamesTrainsPersonalPath(Array<Station> path){
        String[] toRet   = new String[path.size()];

        for(int ii = 0; ii < path.lenght() - 1; ii++){
            int indexStation = personalMap.getNumNode(path.get(ii));
            int indexNextStation = personalMap.getNumNode(path.get(ii + 1));
            
            toRet[ii] = trainNameEdgePersonalMap.get(indexStation).get(indexNextStation);
            
        }

        toRet[path.size() - 1] = "ESTACION FINAL";
        
        return toRet;


    }

    public double calPersoPathDistance(Array<Station> path){
        return personalMap.pathDistance(path);
    }


    public void fillPersonalMap(){

        //Fill nodes
        Station a = new Station("001", "A");
        Station b = new Station("002", "B");
        Station c = new Station("003", "C");
        Station d = new Station("004", "D");
        Station e = new Station("005", "E");
        Station f = new Station("006", "F");
        Station g = new Station("007", "G");
        Station h = new Station("008", "H");
        Station i = new Station("009", "I");
        Station j = new Station("010", "J");
        Station k = new Station("011", "K");

        personalMap.addNode(a);
        personalMap.addNode(b);
        personalMap.addNode(c);
        personalMap.addNode(d);
        personalMap.addNode(e);
        personalMap.addNode(f);
        personalMap.addNode(g);
        personalMap.addNode(h);
        personalMap.addNode(i);
        personalMap.addNode(j);
        personalMap.addNode(k);

        //fill  edges

        Iterator<Route> iter = routes.iterator();

        while(iter.hasNext()){
            Route route = iter.next();

            Array<Station> stations = stationsArray(route.getStations());
        

            for(int ii = 0; ii < stations.size() - 1; ii++ ){
               
                if(personalMap.getValueEdge(stations.get(ii), stations.get(ii + 1)) == 0){
                  
                    int distance = map.pathDistance(map.ShortestPath(stations.get(ii), stations.get(ii + 1)));
                    personalMap.addEdge(stations.get(ii), stations.get(ii + 1), distance);

                    trainNameEdgePersonalMap.get(map.getNumNode(stations.get(ii)) ).
                    set( map.getNumNode(stations.get(ii + 1)), route.getTrains().peek().getName());

                    trainNameEdgePersonalMap.get(map.getNumNode(stations.get(ii + 1)) ).
                    set( map.getNumNode(stations.get(ii)), route.getTrains().peek().getName());


                }


            }


        }


    }

    private Array<Station> stationsArray(LinkedList<Station> list){
        Array<Station> ret = new Array<>(list.size());
        Iterator<Station> iter = list.iterator();

        while (iter.hasNext()) {
            ret.add(iter.next());
        }
        return ret;

    }



  
    public void pullRoutes(){
        Station a = new Station("001", "A");
        Station b = new Station("002", "B");
        Station c = new Station("003", "C");
        Station d = new Station("004", "D");
        Station e = new Station("005", "E");
        Station f = new Station("006", "F");
        Station g = new Station("007", "G");
        Station h = new Station("008", "H");
        Station i = new Station("009", "I");
        Station j = new Station("010", "J");
        Station k = new Station("011", "K");


        LinkedList<Train> trains1 = new LinkedList<>();
        Train train1 = new Train("100001", "JulioTrain", null, 0, 0);
        trains1.add(train1);

        LinkedList<Train> trains2 = new LinkedList<>();
        Train train2 = new Train("100002", "TrenSuperCool", null, 0, 0);
        trains2.add(train2);   

        LinkedList<Station> stationsRoute1 = new LinkedList<>();
        stationsRoute1.add(a);
        stationsRoute1.add(f);
        stationsRoute1.add(g);
        stationsRoute1.add(h);

        LinkedList<Station> stationsRoute2 = new LinkedList<>();
        stationsRoute2.add(g);
        stationsRoute2.add(i);
        stationsRoute2.add(c);

        routes.add(new Route("10001", "Ruta 1", trains1, stationsRoute1,LocalTime.of(10, 0), LocalTime.of(10, 58), 120));
        routes.add(new Route("10002", "Ruta 2", trains2, stationsRoute2, LocalTime.of(14, 0), LocalTime.of(15, 0), 10));
        fillPersonalMap();
    }
    public SeatType[] seatTypeArray(){
        Iterator<SeatType> iter = seatTypes.iterator();
        SeatType [] seatArray = new SeatType[seatTypes.size()];
        int cnt = 0;

        while(iter.hasNext()){
            seatArray[cnt] = iter.next();
            cnt++;
        }
        return seatArray;

    }
    public Station[] stationsArray(){
        Iterator<Station> iter = stations.iterator();
        Station [] stationArray = new Station[stations.size()];
        int cnt = 0;

        while(iter.hasNext()){
            stationArray[cnt] = iter.next();
            cnt++;
        }
        return stationArray;

    }



    public Route[] routeArray(){
        Iterator<Route> iter = routes.iterator();
        Route [] routeArray = new Route[routes.size()];
        int cnt = 0;

        while(iter.hasNext()){
            routeArray[cnt] = iter.next();
            cnt++;
        }
        return routeArray;

    }

    public boolean validateLuggageWeight(double weight){

        if(weight > 80 || weight <= 0){
            return  false;
        }
        return true;
    }

    public IdType[] getSeatTypeArray(){

        IdType[] arrayRet = new IdType[idTypes.size()];
        Iterator<IdType> iter = idTypes.iterator();
        int cnt = 0;
        while(iter.hasNext()){
            arrayRet[cnt] = iter.next();
            cnt++;

        }
        return arrayRet;

    }
    public String[] stationsArrayName(LinkedList<Station> list){
        Iterator<Station> iter = list.iterator();
        String [] nameArray = new String[list.size()];
        int cnt = 0;

        while(iter.hasNext()){
            nameArray[cnt] = iter.next().getName();
            cnt++;
        }
        return nameArray;

    }

    private void fillMap(){
        Station a = new Station("001", "A");
        Station b = new Station("002", "B");
        Station c = new Station("003", "C");
        Station d = new Station("004", "D");
        Station e = new Station("005", "E");
        Station f = new Station("006", "F");
        Station g = new Station("007", "G");
        Station h = new Station("008", "H");
        Station i = new Station("009", "I");
        Station j = new Station("010", "J");
        Station k = new Station("011", "K");

        stations.add(a);
        stations.add(b);
        stations.add(c);
        stations.add(d);
        stations.add(e);
        stations.add(f);
        stations.add(g);
        stations.add(h);
        stations.add(i);
        stations.add(j);
        stations.add(k);

        map.addNode(a);
        map.addNode(b);
        map.addNode(c);
        map.addNode(d);
        map.addNode(e);
        map.addNode(f);
        map.addNode(g);
        map.addNode(h);
        map.addNode(i);
        map.addNode(j);
        map.addNode(k);


        map.addEdge(a, b, 30);
        map.addEdge(a, c, 40);
        map.addEdge(a, d, 50);
        map.addEdge(a, f, 50);

        map.addEdge(c, i, 80);
        map.addEdge(c, j, 120);
        map.addEdge(c, k, 110);

        map.addEdge(d, e, 20);

        map.addEdge(e, f, 65);

        map.addEdge(g, f, 80);
        map.addEdge(g, i, 145);
        map.addEdge(h, g, 30);

    }

    public LinkedList<Train> getTrainsByNames(LinkedList<String> trainNames) {
        LinkedList<Train> ret = new LinkedList<>();

        Iterator<String> iterNames = trainNames.iterator();

        while(iterNames.hasNext()){
            String name = iterNames.next();
            Iterator<Route> iterRoute = routes.iterator();

            while(iterRoute.hasNext()){
                Route route = iterRoute.next();

                if(route.getTrains().peek().getName().equals(name)){
                    ret.add(route.getTrains().peek());
                }

            }


        }
        System.out.println(ret.toString());
        return ret;

    }

    public LocalTime getDepartureTimeRouteByTrain(Train train) {
        LocalTime ret = LocalTime.of(0, 0, 0);
        Iterator<Route> iterRoute = routes.iterator();
        
        while(iterRoute.hasNext()){
            Route route = iterRoute.next();

            if(route.getTrains().peek().getName().equals(train.getName())){
                return route.getDepartureTime();
            }

        }
        return ret;
    }

    public LocalTime getArrivalTimeRouteByTrain(Train train) {
        LocalTime ret = LocalTime.of(0, 0, 0);
        Iterator<Route> iterRoute = routes.iterator();
        
        while(iterRoute.hasNext()){
            Route route = iterRoute.next();

            if(route.getTrains().peek().getName().equals(train.getName())){
                return route.getArrivalTime();
            }

        }
        return ret;
    }
   
}
