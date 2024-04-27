package patitotrains.model.Managers;

import patitotrains.model.domain.Route;
import patitotrains.model.domain.Station;
import patitotrains.model.domain.Train;
import patitotrains.model.remote.client.RemoteServiceManager;
import patitotrains.model.remote.services.ServiceRemote;
import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.noLineal.graph.weightedGraph.Graph;
import raul.Model.util.Iterator.Iterator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalTime;

public class RouteManager {
    private final RemoteServiceManager serviceManager = RemoteServiceManager.getInstance();
    private LinkedList<Station> stations;
    private LinkedList<Route> routesList;
    private LinkedList<Train> trainList;
    private Graph<Station> graph;
    private int routeIdCounter;
    private final ServiceRemote serviceRemote = serviceManager.getServiceRemote();

    public RouteManager() throws RemoteException, NotBoundException {


        stations = new LinkedList<>();
        routesList = new LinkedList<>();
        trainList = new LinkedList<>();
        graph = new Graph<>(11);
        fillGraph();
        pullData();
        routeIdCounter = 1000 + routesList.size();
    }

    public void pullData(){
        try {
            trainList = new LinkedList<>();
            trainList = (LinkedList<Train>) serviceRemote.getAllTrains();
            routesList = new LinkedList<>();
            routesList = (LinkedList<Route>) serviceRemote.getAllRoutes();

        } catch (RemoteException e) {
            System.err.println("Error de comunicación con el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void fillGraph(){

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

        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(f);
        graph.addNode(g);
        graph.addNode(h);
        graph.addNode(i);
        graph.addNode(j);
        graph.addNode(k);


        graph.addEdge(a, b, 30);
        graph.addEdge(a, c, 40);
        graph.addEdge(a, d, 50);
        graph.addEdge(a, f, 50);

        graph.addEdge(c, i, 80);
        graph.addEdge(c, j, 120);
        graph.addEdge(c, k, 110);

        graph.addEdge(d, e, 20);

        graph.addEdge(e, f, 65);

        graph.addEdge(g, f, 80);
        graph.addEdge(g, i, 145);
        graph.addEdge(h, g, 30);

        
    }

    public LinkedList<Station> getStations(){
        return stations;
    }

    public Station getStationByName(String stationName){
        Iterator<Station> iter = stations.iterator();

        while(iter.hasNext()){
            Station station = iter.next();

            if(station.getName().equals(stationName)){
                return station;
            }
        }

        return null;
    }

    public String[] getStationsNames(){
        String[] arrayRet = new String[stations.size()];
        Iterator<Station> iter = stations.iterator();
        int cnt = 0;
        while(iter.hasNext()){
            arrayRet[cnt] = iter.next().getName();
            cnt++;

        }
        return arrayRet;

    }
    public boolean validateStopStations(LinkedList<Station> stopStations){
        Array<Station> arrayStations = new Array<>(stopStations.size());
        arrayStations.add(0, stopStations);

        for(int ii = 0; ii < arrayStations.size() -1; ii++){

            if(arrayStations.get(ii).equals(arrayStations.get( ii + 1))){
                
                return false;
            }

        }
        return true;


    }

    public String[] getTrainsNames(){
        String[] arrayRet = new String[trainList.size()];
        Iterator<Train> iter = trainList.iterator();
        int cnt = 0;
        while(iter.hasNext()){
            arrayRet[cnt] = iter.next().getName();
            cnt++;

        }
        return arrayRet;

    }

    public boolean anyRouteHasTrain(String trainName){
        pullData();
        
        Iterator<Route> iter = routesList.iterator();

        while(iter.hasNext()){
            Route route = iter.next();

            if(route.getTrains().peek().getName().equals(trainName)){
                return true;
            }
        }

        return false;
       
    
    }
    public boolean existRouteByName(String name){
        pullData();
        
        Iterator<Route> iter = routesList.iterator();

        while(iter.hasNext()){
            Route route = iter.next();

            if(route.getName().equals(name)){
                return true;
            }
        }

        return false;
    }

    public boolean editRoute(Route oldUser, Route editedUSer){

         routesList.replace(oldUser, editedUSer, t -> true);
        try {
            serviceRemote.updateRoute(editedUSer);
            pullData();
            return true;
        } catch (RemoteException e) {
            System.err.println("Error de comunicación con el servidor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }


    public boolean addRoute(Route route){
        try{
            sumRouteId();
            routesList.add(route);
            serviceRemote.saveRoute(route);
            pullData();
            return true;
        }catch(RemoteException e){
            System.err.println("Error de comunicación con el servidor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private void sumRouteId(){
        routeIdCounter ++;
    }

    public Route constructRoute(String name, Train train, int hour, int minute, LinkedList<Station> stopStations){
        try{
            String id = Integer.toString(routeIdCounter + 1);
            LinkedList<Train> trains = new LinkedList<>();
            trains.add(train);
            LocalTime departureTime = LocalTime.of(hour, minute, 0);
            double routeDistance = calRouteDistance(stopStations);
            double hourInRoute = routeDistance/250;
            int minutesInRoad = (int)(hourInRoute*60);
            minutesInRoad += 20*(stopStations.size() - 2);

            LocalTime arrivalTime = departureTime.plusMinutes(minutesInRoad);

            return new Route(id, name, trains, stopStations, departureTime, arrivalTime, routeDistance);

        }catch(Exception e){
            return null;
        }
    }

    private double calRouteDistance(LinkedList<Station> stopStations) {
        double distance = 0;
        Array<Station> arrayStopStations = new Array<>(stopStations.size());
        Array<Station> midStations;
        arrayStopStations.add(0, stationsArray(stopStations));

        for(int ii = 0 ; ii < arrayStopStations.size() - 1; ii++){
            midStations = graph.ShortestPath(arrayStopStations.get(ii), arrayStopStations.get(ii + 1));
            distance += graph.pathDistance(midStations);
        }
        return distance;
        
    }

    public boolean validateHour(int hour){
        if( hour < 0 || hour > 23){
            return false;
        }
        return true;
    }   
    public boolean validateMinute(int hour){
        if( hour < 0 || hour > 59){
            return false;
        }
        return true;
    }

    public Train getTrainByName(String name){
        pullData();

        Iterator<Train> iter = trainList.iterator();

        while(iter.hasNext()){
            Train train = iter.next();

            if(train.getName().equals(name)){
                return train;
            }
        }

        return null;

    }
    public LinkedList<Route> searchRoutes(String routeToSearch){
        pullData();
        int strLen = routeToSearch.length();
        LinkedList<Route> toReturn = new LinkedList<>();
        Route route;
        String tName; 
        String tId;
        boolean finded;

        if (strLen < 4){
            return routesList;
        }

        Iterator<Route> iter = routesList.iterator();

        while(iter.hasNext()){
  
            finded = false;

            route = iter.next();
            tName = route.getName();
            tId= route.getId();

            if(tName.length() >= strLen){
                if(tName.substring(0, strLen).equals(routeToSearch)){
                    finded = true;
                }
            }

            if(tId.length() >= strLen){
                if(tId.substring(0, strLen).equals(routeToSearch)){
                    finded = true;
                }
            }
            
            if(finded){
                toReturn.add(route);
            }
        }

        return toReturn;

    }



    public Route[] routeArray(){
        Iterator<Route> iter = routesList.iterator();
        Route [] routeArray = new Route[routesList.size()];
        int cnt = 0;

        while(iter.hasNext()){
            routeArray[cnt] = iter.next();
            cnt++;
        }
        return routeArray;

    }

    public Route[] routeArray(LinkedList<Route> list){
        Iterator<Route> iter = list.iterator();
        Route [] routeArray = new Route[list.size()];
        int cnt = 0;

        while(iter.hasNext()){
            routeArray[cnt] = iter.next();
            cnt++;
        }
        return routeArray;

    }

    public Station[] stationsArray(LinkedList<Station> list){
        Iterator<Station> iter = list.iterator();
        Station [] stationArray = new Station[list.size()];
        int cnt = 0;

        while(iter.hasNext()){
            stationArray[cnt] = iter.next();
            cnt++;
        }
        return stationArray;

    }

}
