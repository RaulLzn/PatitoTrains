package patitotrains.model.Managers.interfaceManagers;


import patitotrains.model.domain.Train;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import patitotrains.model.domain.types.TrainType;

/**
 * Interfaz de la clase TrainManager
 */
public interface TrainManagerInterface {
    

    /**
     * Método que obtiene los datos de los trenes
     */
    void pullData();

    /**
     * Método que obtiene los datos de los trenes
     * @return Lista de trenes
     */
    boolean existTrainByName(String name);
/**
     * Método que obtiene los datos de los trenes
     * @return Lista de trenes
     */
    TrainType getTrainTypeByDescription(String description);
/**
     * Método que obtiene los datos de los trenes
     * @return Lista de trenes
     */
    String[] trainTypesDescription();

    /**
     * Método que obtiene los datos de los trenes
     * @return Lista de trenes
     */
    LinkedList<Train> searchTrains(String trainToSearch);

    /**
     * Método que obtiene los datos de los trenes
     * @return Lista de trenes
     */
    boolean addTrain(String id, String name, TrainType type, int amtPassengerWagons);

    /**
     * Método que obtiene los datos de los trenes
     * @return Lista de trenes
     */
    boolean editTrain(String id, String newName, int newAmtPassangerWagons, boolean newStatus);

    /**
     * Método que obtiene los datos de los trenes
     * @return Lista de trenes
     */
    boolean validateAmtPassengerWagons(int amtPassengerWagons, TrainType type);

    /**
     * Método que obtiene los datos de los trenes
     * @return Lista de trenes
     */
    int calculateAmtLugaggeWagons(int amtPassengerWagons);

    /**
     * Método que obtiene los datos de los trenes
     * @return Lista de trenes
     */
    Train[] trainArray();

    /**
     * Método que obtiene los datos de los trenes
     * @return Lista de trenes
     */
    Train[] trainArray(LinkedList<Train> list);

    /**
     * Método que obtiene los datos de los trenes
     * @return Lista de trenes
     */
    int getTrainIdCounter();

    /**
     * Método que obtiene los datos de los trenes
     */
    void setTrainIdCounter(int trainIdCounter);


}