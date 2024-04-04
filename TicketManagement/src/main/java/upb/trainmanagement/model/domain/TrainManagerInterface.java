package upb.trainmanagement.model.domain;

import com.edu.upb.linkedList.doubly.LinkedList;

import upb.trainmanagement.model.domain.types.TrainType;

public interface TrainManagerInterface {

    LinkedList<Train> getTrainList();

    void setTrainList(LinkedList<Train> trainList);

    boolean addTrain(Train train);

    Train getTrainById(String id);

    boolean existTrainById(String id);

    boolean existTrainByName(String name);

    TrainType getTrainTypeById(String id);

    TrainType getTrainTypeByDescription(String description);

    String[] trainTypesDescription();

    LinkedList<Train> searchTrains(String trainToSearch);

    boolean addTrain(String id, String name, TrainType type, int amtPassengerWagons);

    boolean editTrain(String id, String newName, int newAmtPassengerWagons, boolean newStatus);

    boolean validateAmtPassengerWagons(int amtPassengerWagons, TrainType type);

    int calculateAmtLugaggeWagons(int amtPassengerWagons);

    Train[] trainArray();

    Train[] trainArray(LinkedList<Train> list);

    LinkedList<TrainType> getTrainTypes();

    void setTrainTypes(LinkedList<TrainType> trainTypes);

    String toString();

    int getTrainIdCounter();
    
    void setTrainIdCounter(int trainIdCounter);
}