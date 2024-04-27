package patitotrains.model.Managers.interfaceManagers;


import patitotrains.model.domain.Train;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import patitotrains.model.domain.types.TrainType;

public interface TrainManagerInterface {
    

    void pullData();

    boolean existTrainByName(String name);

    TrainType getTrainTypeByDescription(String description);

    String[] trainTypesDescription();

    LinkedList<Train> searchTrains(String trainToSearch);

    boolean addTrain(String id, String name, TrainType type, int amtPassengerWagons);

    boolean editTrain(String id, String newName, int newAmtPassangerWagons, boolean newStatus);

    boolean validateAmtPassengerWagons(int amtPassengerWagons, TrainType type);

    int calculateAmtLugaggeWagons(int amtPassengerWagons);

    Train[] trainArray();

    Train[] trainArray(LinkedList<Train> list);

    int getTrainIdCounter();

    void setTrainIdCounter(int trainIdCounter);


}