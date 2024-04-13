package upb.administrator.model.Managers;

import com.edu.upb.linkedList.doubly.LinkedList;
import com.edu.upb.util.iterator.Iterator;

import upb.administrator.model.Managers.interfaceManagers.TrainManagerInterface;
import upb.administrator.model.domain.Train;
import upb.administrator.model.domain.types.TrainType;

import java.util.function.Function;

public class TrainManager implements TrainManagerInterface{
    
    private LinkedList<Train> trainList;
    private LinkedList<TrainType> trainTypes;
    private int trainIdCounter;


    public TrainManager() {
        trainList = new LinkedList<>();
        trainTypes = new LinkedList<>();
        pullData();
       
    }

    public void pullData(){
        trainIdCounter = 10036;
        TrainType arnonld = new TrainType("001", "Arnold", 32) ;
        TrainType mercedes = new TrainType("002", "Mercedes", 28) ;
        trainTypes.add(mercedes);
        trainTypes.add(arnonld);

        addTrain("10000", "Charala Train", arnonld, 6);
        addTrain("10001", "Granados Train",arnonld, 6);
        addTrain("10002", "Stormwind Express", arnonld, 8);
        addTrain("10003", "Ironforge Limited", mercedes, 15);
        addTrain("10004", "Orgrimmar Express",arnonld, 10);
        addTrain("10005", "Darnassus Local", mercedes, 5);
        addTrain("10006", "Undercity Commuter", arnonld, 12);
        addTrain("10007", "Silvermoon Shuttle", mercedes, 7);
        addTrain("10008", "Exodar Express", arnonld, 14);
        addTrain("10009", "Thunder Bluff Transit",mercedes, 9);
        addTrain("10010", "Gnomeregan Rapid", arnonld, 11);
        addTrain("10011", "Sen'jin Sprinter", mercedes, 6);
        addTrain("10012", "Stranglethorn Express", arnonld, 18);
        addTrain("10013", "Winterspring Wanderer", mercedes, 13);
        addTrain("10014", "Darkshore Deluxe", arnonld, 8);
        addTrain("10015", "Durotar Bullet", mercedes, 17);
        addTrain("10016", "Thousand Needles Transit", arnonld, 4);
        addTrain("10017", "Feralas Flyer", mercedes, 15);
        addTrain("10018", "Tanaris Thunder", arnonld, 10);
        addTrain("10019", "Silithus Swift", mercedes, 12);
        addTrain("10020", "Desolace Express", arnonld, 5);
        addTrain("10021", "Mulgore Mover", mercedes, 16);
        addTrain("10022", "Felwood Flyer",arnonld, 7);
        addTrain("10023", "Ashenvale Arrow", mercedes, 14);
        addTrain("10024", "Azshara Zephyr", arnonld, 9);
        addTrain("10025", "Un'Goro Underground", mercedes, 11);
        addTrain("10026", "Wailing Caverns Cruiser", arnonld, 6);
        addTrain("10027", "Train Tracker", mercedes, 7);
        addTrain("10028", "Train Transporter", arnonld, 14);
        addTrain("10029", "Train Traveler", mercedes, 9);
        addTrain("10030", "Train Trailblazer", arnonld, 11);
        addTrain("10031", "Train Transit", mercedes, 6);
        addTrain("10032", "Train Thunder", arnonld, 18);
        addTrain("10033", "Train Travelogue", mercedes, 13);
        addTrain("10034", "Train Trekker", arnonld, 8);
        addTrain("10035", "Train Turbo", mercedes, 17);
        addTrain("10036", "Train Tornado", arnonld, 4);


        getTrainById("10001").setDisabled(true);
        getTrainById("10002").setDisabled(true);
        getTrainById("10002").setOnJourney(true);

        getTrainById("10003").setOnJourney(true);
        getTrainById("10004").setOnJourney(true);
        getTrainById("10005").setOnJourney(true);

        getTrainById("10005").setMileage(30);
        getTrainById("10005").setMileage(300);
        getTrainById("10008").setMileage(15000);
        getTrainById("10012").setMileage(6000);
        getTrainById("10016").setMileage(45000);
        getTrainById("10020").setMileage(80000);
        getTrainById("10024").setMileage(20000);
        getTrainById("10028").setMileage(7500);
        getTrainById("10032").setMileage(42000);
        getTrainById("10036").setMileage(90000);
        getTrainById("10001").setMileage(60000);

    }
    
    private Train getTrainById(String id){

        Iterator<Train> iter = trainList.iterator();

        while(iter.hasNext()){
            Train train = iter.next();

            if(train.getId().equals(id)){
                return train;
            }
        }

        return null;

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

            if(!existTrainByName(id)){
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
    
    public int getTrainIdCounter() {
        return trainIdCounter;
    }

    public void setTrainIdCounter(int trainIdCounter) {
        this.trainIdCounter = trainIdCounter;
    }

}
