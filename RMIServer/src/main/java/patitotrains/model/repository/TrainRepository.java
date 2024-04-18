package patitotrains.model.repository;

import patitotrains.model.domain.Train;
import patitotrains.model.domain.types.TrainType;
import patitotrains.model.repository.entity.TrainEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;

import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

/**
 * Clase que representa un repositorio de trenes
 */
public class TrainRepository {


    private final String TRAIN_DATA_FILE = "ruta/hacia/el/archivo.json"; // Ruta del archivo JSON
    private final FileJsonAdapter<TrainEntity> fileJsonAdapter; // Adaptador de archivos JSON

    /**
     * Constructor de la clase
     */
    public TrainRepository() {
        this.fileJsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Método que obtiene todos los trenes del archivo JSON
     * @return Lista de trenes
     */
    public List<Train> getAllTrains() {
        List<Train> trains = new LinkedList<>();
        List<TrainEntity> trainEntities = fileJsonAdapter.getObjects(TRAIN_DATA_FILE, TrainEntity[].class);
        Iterator<TrainEntity> iterator = trainEntities.iterator();

        while (iterator.hasNext()) {
            TrainEntity trainEntity = iterator.next();
            Train train = mapToTrain(trainEntity);
            trains.add(train);
        }

        return trains;
    }

    /**
     * Método que guarda un tren en el archivo JSON
     * @param train Tren a guardar
     * @return true si se guardó correctamente, false en caso contrario
     */
    public boolean saveTrain(Train train) {
        TrainEntity trainEntity = mapToTrainEntity(train);
        List<TrainEntity> trainEntities = fileJsonAdapter.getObjects(TRAIN_DATA_FILE, TrainEntity[].class);
        trainEntities.add(trainEntity);
        return fileJsonAdapter.writeObjects(TRAIN_DATA_FILE, trainEntities);
    }

    /**
     * Método que obtiene un tren por su id
     * @param id Id del tren
     * @return Tren
     */
    public Train getTrainById(String id) {
        List<TrainEntity> trainEntities = fileJsonAdapter.getObjects(TRAIN_DATA_FILE, TrainEntity[].class);
        Iterator<TrainEntity> iterator = trainEntities.iterator();

        while (iterator.hasNext()) {
            TrainEntity trainEntity = iterator.next();
            if (trainEntity.getId().equals(id)) {
                return mapToTrain(trainEntity);
            }
        }

        return null;
    }

    /**
     * Método que actualiza un tren en el archivo JSON
     * @param train Tren a actualizar
     * @return true si se actualizó correctamente, false en caso contrario
     */
    public boolean updateTrain(Train train) {
        List<TrainEntity> trainEntities = fileJsonAdapter.getObjects(TRAIN_DATA_FILE, TrainEntity[].class);
        Iterator<TrainEntity> iterator = trainEntities.iterator();

        while (iterator.hasNext()) {
            TrainEntity trainEntity = iterator.next();
            if (trainEntity.getId().equals(train.getId())) {
                trainEntities.remove(trainEntity);
                trainEntities.add(mapToTrainEntity(train));
                return fileJsonAdapter.writeObjects(TRAIN_DATA_FILE, trainEntities);
            }
        }

        return false;
    }

    /**
     * Método que elimina un tren del archivo JSON
     * @param id Id del tren
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean deleteTrain(String id) {
        List<TrainEntity> trainEntities = fileJsonAdapter.getObjects(TRAIN_DATA_FILE, TrainEntity[].class);
        Iterator<TrainEntity> iterator = trainEntities.iterator();

        while (iterator.hasNext()) {
            TrainEntity trainEntity = iterator.next();
            if (trainEntity.getId().equals(id)) {
                trainEntities.remove(trainEntity);
                return fileJsonAdapter.writeObjects(TRAIN_DATA_FILE, trainEntities);
            }
        }

        return false;
    }

    /**
     * Método que mapea un objeto TrainEntity a Train
     * @param entity Objeto TrainEntity
     * @return Objeto Train
     */
    private Train mapToTrain(TrainEntity entity) {
        TrainType type = getTrainTypeFromString(entity.getType());
        Train train = new Train(entity.getId(), entity.getName(), type,
                entity.getAmtPassengerWagons(), entity.getAmtLuggageWagons());
        train.setMileage(entity.getMileage());
        train.setOnJourney(entity.isOnJourney());
        train.setDisabled(entity.isDisabled());
        return train;
    }

    /**
     * Método que obtiene un objeto TrainType a partir de un String
     * @param typeString String del tipo de tren
     * @return Objeto TrainType
     */
    private TrainType getTrainTypeFromString(String typeString) {
        return new TrainType("", typeString, 0);
    }

    /**
     * Método que mapea un objeto Train a TrainEntity
     * @param train Objeto Train
     * @return Objeto TrainEntity
     */
    private TrainEntity mapToTrainEntity(Train train) {
        String type = train.getType().toString();
        TrainEntity trainEntity = new TrainEntity(train.getId(), train.getName(), train.getMileage(),
                type, train.getPassengerWagons().size(),
                train.getLuggageWagons().size(),
                train.isOnJourney(), train.isDisabled());
        return trainEntity;
    }
}
