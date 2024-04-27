package patitotrains.model.Managers;

import patitotrains.model.Managers.interfaceManagers.TrainManagerInterface;
import patitotrains.model.domain.ContainerWagon;
import patitotrains.model.domain.PassengerWagon;
import patitotrains.model.domain.Train;
import patitotrains.model.domain.types.TrainType;
import patitotrains.model.remote.client.RemoteServiceManager;
import patitotrains.model.remote.services.ServiceRemote;
import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class TrainManager implements TrainManagerInterface {

    private final RemoteServiceManager serviceManager = RemoteServiceManager.getInstance();
    private final ServiceRemote serviceRemote = serviceManager.getServiceRemote();

    private LinkedList<Train> trainList;
    private Array<TrainType> trainTypes = new Array<>(2);
    private int trainIdCounter;


    public TrainManager() throws RemoteException, NotBoundException {
        trainList = new LinkedList<>();
        pullData();
        trainIdCounter = 1000+trainList.size();

        TrainType arnonld = new TrainType("001", "Arnold", 32) ;
        TrainType mercedes = new TrainType("002", "Mercedes", 28) ;
        trainTypes.add(mercedes);
        trainTypes.add(arnonld);


    }

    public void pullData() {
        try {
            trainList = (LinkedList<Train>) serviceRemote.getAllTrains();
        } catch (RemoteException e) {
            System.err.println("Error de comunicación con el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public Train getTrainById(String id){

        try {
            Iterator<Train> iter = trainList.iterator();

            while(iter.hasNext()){
                Train train = iter.next();

                if(train.getId().equals(id)){
                    return train;
                }
            }
            return serviceRemote.getTrainById(id);
        } catch (RemoteException e) {
            System.err.println("Error de comunicación con el servidor: " + e.getMessage());
            e.printStackTrace();
        }

        return null;

    }

    public boolean existTrainByName(String name){

        pullData();

        Iterator<Train> iter = trainList.iterator();

        while(iter.hasNext()){
            Train train = iter.next();

            if(train.getName().equals(name)){
                return true;
            }
        }

        return false;


    }

    public boolean existTrainById(String id){

        pullData();

        Iterator<Train> iter = trainList.iterator();

        while(iter.hasNext()){
            Train train = iter.next();

            if(train.getId().equals(id)){
                return true;
            }
        }

        return false;


    }
  
    public TrainType getTrainTypeByDescription(String description){

        pullData();

        Iterator<TrainType> iter = trainTypes.iterator();

        while(iter.hasNext()){
            TrainType type = iter.next();

            if(type.getDescription().equals(description)){
                return type;
            }
        }

        return null;

    }
    public  String[] trainTypesDescription(){

        pullData();

        String [] trianTypesDescription  = new String[trainTypes.size()];
        int cnt = 0;
        Iterator<TrainType> iter = trainTypes.iterator();

        while(iter.hasNext()){
            trianTypesDescription[cnt] = iter.next().getDescription();
            cnt++;
        }
        return trianTypesDescription;
    }



    public LinkedList<Train> searchTrains(String trainToSearch) {

        pullData();

        int strLen = trainToSearch.length();
        LinkedList<Train> toReturn = new LinkedList<>();
        Train train;
        String tName; 
        String tId;
        boolean finded;

        if (strLen < 4){
            return trainList;
        }

        Iterator<Train> iter = trainList.iterator();

        while(iter.hasNext()){
  
            finded = false;

            train = iter.next();
            tName = train.getName();
            tId= train.getId();

            if(tName.length() >= strLen){
                if(tName.substring(0, strLen).equals(trainToSearch)){
                    finded = true;
                }
            }

            if(tId.length() >= strLen){
                if(tId.substring(0, strLen).equals(trainToSearch)){
                    finded = true;
                }
            }
            
            if(finded){
                toReturn.add(train);
            }
        }

        return toReturn;

    }

    public boolean addTrain(String id, String name, TrainType type, int amtPassengerWagons){
        try {

            pullData();

            if(!validateAmtPassengerWagons(amtPassengerWagons, type)){
                return false;
            }

            int amtLuggageWagons = calculateAmtLugaggeWagons(amtPassengerWagons);

            Train train = new Train(id, name, type, amtPassengerWagons, amtLuggageWagons);
            int idp = Integer.parseInt(id)*100;
            for (int i = 0; i < amtPassengerWagons; i++) {
                train.getPassengerWagons().add((new PassengerWagon(Integer.toString(idp+i), i == 0)));
                serviceRemote.savePassengerWagon(train.getPassengerWagons().get(i));
            }

            int idc = Integer.parseInt(id)*100;
            for (int i = 0; i < amtLuggageWagons; i++) {
                train.getLuggageWagons().add((new ContainerWagon(Integer.toString(idc+i))));
                serviceRemote.saveContainerWagon(train.getLuggageWagons().get(i));
            }

            trainList.add(train);
            serviceRemote.saveTrain(train);
            return true;
        } catch (RemoteException e) {
            System.err.println("Error de comunicación con el servidor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean editTrain(String id, String newName, int newAmtPassengerWagons, boolean newStatus) {
        try {
            Train train = getTrainById(id);

            if (train == null) {
                return false;
            }

            if (!validateAmtPassengerWagons(newAmtPassengerWagons, train.getType())) {
                return false;
            }

            if (!existTrainById(id)) {
                return false;
            }

            // Eliminar todos los vagones existentes del tren
            Iterator<PassengerWagon> passengerWagonIterator = train.getPassengerWagons().iterator();
            while (passengerWagonIterator.hasNext()) {
                serviceRemote.deletePassengerWagon(passengerWagonIterator.next().getId());
            }

            Iterator<ContainerWagon> containerWagonIterator = train.getLuggageWagons().iterator();
            while (containerWagonIterator.hasNext()) {
                serviceRemote.deleteContainerWagon(containerWagonIterator.next().getId());
            }

            // Crear nuevos vagones según el nuevo número especificado
            train.setPassengerWagons(new Array<>(newAmtPassengerWagons));
            train.setLuggageWagons(new Array<>(calculateAmtLugaggeWagons(newAmtPassengerWagons)));

            int idp = Integer.parseInt(id) * 100;
            for (int i = 0; i < newAmtPassengerWagons; i++) {
                train.getPassengerWagons().add(new PassengerWagon(Integer.toString(idp + i), i == 0));
                serviceRemote.savePassengerWagon(train.getPassengerWagons().get(i));
            }

            int idc = Integer.parseInt(id) * 100;
            for (int i = 0; i < calculateAmtLugaggeWagons(newAmtPassengerWagons); i++) {
                train.getLuggageWagons().add(new ContainerWagon(Integer.toString(idc + i)));
                serviceRemote.saveContainerWagon(train.getLuggageWagons().get(i));
            }

            train.setName(newName);
            train.setDisabled(newStatus);
            serviceRemote.updateTrain(train);
            pullData();
            return true;
        } catch (RemoteException e) {
            System.err.println("Error de comunicación con el servidor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }



    public boolean validateAmtPassengerWagons(int amtPassengerWagons, TrainType type){

        pullData();

        int cargo = type.getCargoCapacity();
        int amtLuggageWagons = calculateAmtLugaggeWagons(amtPassengerWagons);
        int totalWagons = amtLuggageWagons + amtPassengerWagons;
        if(amtPassengerWagons <= 0){
            return false;
        }

        if(cargo < totalWagons){
            return false;
        }
        return true;
    }

    public int calculateAmtLugaggeWagons(int amtPassengerWagons){
        pullData();

        return (amtPassengerWagons + 1)/2;
        
    }

    public Train[] trainArray(){
        Train [] trainArray = new Train[trainList.size()];
        int cnt = 0;
        Iterator<Train> iter = trainList.iterator();

        while(iter.hasNext()){
            trainArray[cnt] = iter.next();
            cnt++;
        }
        return trainArray;
    }

   

    public Train[] trainArray(LinkedList<Train> list){
        Train [] trainArray = new Train[list.size()];
        int cnt = 0;
        Iterator<Train> iter = list.iterator();

        while(iter.hasNext()){
            trainArray[cnt] = iter.next();
            cnt++;
        }
        return trainArray;
    }
    
    public int getTrainIdCounter() {

        pullData();
        return trainIdCounter;
    }

    public void setTrainIdCounter(int trainIdCounter) {
        pullData();
        this.trainIdCounter = trainIdCounter;
    }

}
