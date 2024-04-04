package upb.trainmanagement.model.domain;

import com.edu.upb.linkedList.doubly.LinkedList;
import com.edu.upb.util.iterator.Iterator;

import upb.trainmanagement.model.domain.types.TrainType;

import java.util.function.Function;

public class TrianManager implements TrainManagerInterface{
    
    private LinkedList<Train> trainList;
    private LinkedList<TrainType> trainTypes;
    private int trainIdCounter;

    

    public TrianManager() {
        trainList = new LinkedList<>();
        trainTypes = new LinkedList<>();
        trainIdCounter = 0; 

        // A ELIMINAR (TEMPORAL HASTA QUE SE TENGA LA BASE DE DATOS)
        trainTypes.add(new TrainType("001", "Arnold", 32) );
        trainTypes.add(new TrainType("002", "Mercedes", 28) );

        // USADO PARA LAS PRUEBAS
    }

    public LinkedList<Train> getTrainList() {
        return trainList;
    }

    public void setTrainList(LinkedList<Train> trainList) {
        this.trainList = trainList;
    }

    public boolean addTrain( Train train){
        try{
            trainList.add(train);
            return true;
        }catch( Exception e){
            return false;
        }
    }

    
    public  Train getTrainById(String id){

        Iterator<Train> iter = trainList.iterator();

        while(iter.hasNext()){
            Train train = iter.next();

            if(train.getId().equals(id)){
                return train;
            }
        }

        return null;

    }

    public boolean existTrainById(String id){
        
        Iterator<Train> iter = trainList.iterator();

        while(iter.hasNext()){
            Train train = iter.next();

            if(train.getId().equals(id)){
                return true;
            }
        }

        return false;
       
    
    }

    public boolean existTrainByName(String name){
        
        Iterator<Train> iter = trainList.iterator();

        while(iter.hasNext()){
            Train train = iter.next();

            if(train.getName().equals(name)){
                return true;
            }
        }

        return false;
       
    
    }

    public TrainType getTrainTypeById(String id){

        Iterator<TrainType> iter = trainTypes.iterator();

        while(iter.hasNext()){
            TrainType type = iter.next();

            if(type.getId().equals(id)){
                return type;
            }
        }

        return null;

    }
    public TrainType getTrainTypeByDescription(String description){

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
        String [] trianTypesDescription  = new String[trainTypes.size()];
        int cnt = 0;
        Iterator<TrainType> iter = trainTypes.iterator();

        while(iter.hasNext()){
            trianTypesDescription[cnt] = iter.next().getDescription();
            cnt++;
        }
        return trianTypesDescription;
    }



    public LinkedList<Train> searchTrains(String trainToSearch){
        int strLen = trainToSearch.length();
        LinkedList<Train> toReturn = new LinkedList<>();
        Train train;
        String tName; 
        String tId;

        boolean toCompare;
        boolean finded;

        if (strLen < 4){
            return trainList;
        }

        Iterator<Train> iter = trainList.iterator();

        while(iter.hasNext()){
            toCompare = false;
            finded = false;


            train = iter.next();
            tName = train.getName();
            tId= train.getId();

            if(tName.length() >= strLen){
                toCompare = true;
            }

            if(toCompare){
                if(tName.substring(0, strLen).equals(trainToSearch)){
                    finded = true;
                }
            }

            toCompare = false;


            if(tId.length() >= strLen){
                toCompare = true;
            }

            if(toCompare){
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
        try{
            int amtLuggageWagons = calculateAmtLugaggeWagons(amtPassengerWagons);
            Train trainToAdd = new Train(id, name, type, amtPassengerWagons, amtLuggageWagons);
            
            if(trainList.add(trainToAdd)){
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }

    public boolean editTrain(String id, String newName, int newAmtPassangerWagons, boolean newStatus){
        try{   

            if(!existTrainById(id)){
                return false;
            }
            Function<Train, Void> action = new Function<Train,Void>() {
            
                @Override
                public Void apply(Train t) {
                    if(t.getId().equals(id)){
                        t.setDisabled(newStatus);
                        t.setName(newName);
                        t.getPassengerWagons().dimension(newAmtPassangerWagons);
                        t.getLuggageWagons().dimension(calculateAmtLugaggeWagons(newAmtPassangerWagons));
                    }else{
                    }
                    return null;
             
                }
            };

            trainList.forEach(action);
            return true;
        }catch(Exception e){
            return false;
        }


    }


    public boolean validateAmtPassengerWagons(int amtPassengerWagons, TrainType type){
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
    
    public LinkedList<TrainType> getTrainTypes() {
        return trainTypes;
    }
    public void setTrainTypes(LinkedList<TrainType> trainTypes) {
        this.trainTypes = trainTypes;
    }

    public int getTrainIdCounter() {
        return trainIdCounter;
    }

    public void setTrainIdCounter(int trainIdCounter) {
        this.trainIdCounter = trainIdCounter;
    }

  

}
