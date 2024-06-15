package patitotrains.model.repository;

import patitotrains.model.domain.ContainerWagon;
import patitotrains.model.domain.PassengerWagon;
import patitotrains.model.domain.Train;
import patitotrains.model.repository.entity.TrainEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;
import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Repositorio de trenes.
 */
public class TrainRepository implements Serializable {
    private static final String FILE_PATH = "Train.Json";
    private final FileJsonAdapter<TrainEntity> jsonAdapter;

    public TrainRepository() {
        this.jsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Obtiene todos los trenes.
     *
     * @return Lista de trenes.
     */
    public List<Train> getAllTrains() {
        List<TrainEntity> trainEntities = jsonAdapter.getObjects(FILE_PATH, TrainEntity[].class);
        List<Train> trains = new LinkedList<>();

        Iterator<TrainEntity> iterator = trainEntities.iterator();
        while (iterator.hasNext()) {
            trains.add(mapToTrain(iterator.next()));
        }

        return trains;
    }

    /**
     * Guarda un tren en el archivo JSON.
     *
     * @param train Tren a guardar.
     * @return Verdadero si el tren se guardó correctamente, falso en caso contrario.
     */
    public boolean saveTrain(Train train) {
        List<TrainEntity> trainEntities = jsonAdapter.getObjects(FILE_PATH, TrainEntity[].class);
        TrainEntity entity = mapToTrainEntity(train);
        trainEntities.add(entity);
        return jsonAdapter.writeObjects(FILE_PATH, trainEntities);
    }

    /**
     * Obtiene un tren por su ID.
     *
     * @param id ID del tren.
     * @return Tren con el ID especificado, o nulo si no se encontró.
     */
    public Train getTrainById(String id) {
        List<TrainEntity> trainEntities = jsonAdapter.getObjects(FILE_PATH, TrainEntity[].class);
        Iterator<TrainEntity> iterator = trainEntities.iterator();
        while (iterator.hasNext()) {
            TrainEntity entity = iterator.next();
            if (entity.getId().equals(id)) {
                return mapToTrain(entity);
            }
        }
        return null;
    }

    /**
     * Obtiene un tren por su nombre.
     *
     * @param entity Nombre del tren.
     * @return Tren con el nombre especificado, o nulo si no se encontró.
     */
    private Train mapToTrain(TrainEntity entity) {
        Train train = new Train(entity.getId(), entity.getName(), entity.getMileage(), entity.getType(),
                new Array<>(entity.getPassengerWagonIds().length), new Array<>(entity.getLuggageWagonIds().length),
                entity.isOnJourney(), entity.isDisabled());

        PassengerWagonRepository passengerWagonRepository = new PassengerWagonRepository();
        List<PassengerWagon> passengerWagons = passengerWagonRepository.getPassengerWagonsByIds(new Array<>(entity.getPassengerWagonIds()));
        Iterator<PassengerWagon> passengerWagonIterator = passengerWagons.iterator();
        while (passengerWagonIterator.hasNext()) {
            train.getPassengerWagons().add(passengerWagonIterator.next());
        }

        ContainerWagonRepository containerWagonRepository = new ContainerWagonRepository();
        List<ContainerWagon> containerWagons = containerWagonRepository.getContainerWagonsByIds(new Array<>(entity.getLuggageWagonIds()));
        Iterator<ContainerWagon> containerWagonIterator = containerWagons.iterator();
        while (containerWagonIterator.hasNext()) {
            train.getLuggageWagons().add(containerWagonIterator.next());
        }

        return train;
    }

    /**
     * Mapea un objeto Train a un objeto TrainEntity.
     *
     * @param train Tren a mapear.
     * @return Entidad TrainEntity mapeada.
     */
    private TrainEntity mapToTrainEntity(Train train) {
        TrainEntity entity = new TrainEntity(train.getId(), train.getName(), train.getMileage(), train.getType(),
                new String[train.getPassengerWagons().size()], new String[train.getLuggageWagons().size()],
                train.isOnJourney(), train.isDisabled());

        // Lógica para mapear los IDs de los vagones de pasajeros
        for (int i = 0; i < train.getPassengerWagons().size(); i++) {
            entity.getPassengerWagonIds()[i] = train.getPassengerWagons().get(i).getId();
        }

        // Lógica para mapear los IDs de los vagones de equipaje
        for (int i = 0; i < train.getLuggageWagons().size(); i++) {
            entity.getLuggageWagonIds()[i] = train.getLuggageWagons().get(i).getId();
        }

        return entity;
    }

    /**
     * Actualiza un tren en el archivo JSON.
     *
     * @param train Tren a actualizar.
     * @return Verdadero si el tren se actualizó correctamente, falso en caso contrario.
     */
    public boolean updateTrain(Train train) {
        List<TrainEntity> trainEntities = jsonAdapter.getObjects(FILE_PATH, TrainEntity[].class);
        Iterator<TrainEntity> iterator = trainEntities.iterator();
        while (iterator.hasNext()) {
            TrainEntity entity = iterator.next();
            if (entity.getId().equals(train.getId())) {
                entity.setName(train.getName());
                entity.setMileage(train.getMileage());
                entity.setType(train.getType());
                entity.setPassengerWagonIds(new String[train.getPassengerWagons().size()]);
                entity.setLuggageWagonIds(new String[train.getLuggageWagons().size()]);
                entity.setOnJourney(train.isOnJourney());
                entity.setDisabled(train.isDisabled());

                // Lógica para mapear los IDs de los vagones de pasajeros
                for (int i = 0; i < train.getPassengerWagons().size(); i++) {
                    entity.getPassengerWagonIds()[i] = train.getPassengerWagons().get(i).getId();
                }

                // Lógica para mapear los IDs de los vagones de equipaje
                for (int i = 0; i < train.getLuggageWagons().size(); i++) {
                    entity.getLuggageWagonIds()[i] = train.getLuggageWagons().get(i).getId();
                }

                return jsonAdapter.writeObjects(FILE_PATH, trainEntities);
            }
        }
        return false;
    }

    /**
     * Agrega un vagón de pasajeros a un tren.
     *
     * @param trainId ID del tren.
     * @param passengerWagon Vagón de pasajeros a agregar.
     * @return Verdadero si el vagón de pasajeros se agregó correctamente, falso en caso contrario.
     */
    public boolean addPassengerWagonToTrain(String trainId, PassengerWagon passengerWagon) {
        List<TrainEntity> trainEntities = jsonAdapter.getObjects(FILE_PATH, TrainEntity[].class);
        Iterator<TrainEntity> iterator = trainEntities.iterator();
        while (iterator.hasNext()) {
            TrainEntity entity = iterator.next();
            if (entity.getId().equals(trainId)) {
                entity.setPassengerWagonIds(Arrays.copyOf(entity.getPassengerWagonIds(), entity.getPassengerWagonIds().length + 1));
                entity.getPassengerWagonIds()[entity.getPassengerWagonIds().length - 1] = passengerWagon.getId();

                return jsonAdapter.writeObjects(FILE_PATH, trainEntities);
            }
        }
        return false;
    }

    /**
     * Agrega un vagón de equipaje a un tren.
     *
     * @param trainId ID del tren.
     * @param containerWagon Vagón de equipaje a agregar.
     * @return Verdadero si el vagón de equipaje se agregó correctamente, falso en caso contrario.
     */
    public boolean addContainerWagonToTrain(String trainId, ContainerWagon containerWagon) {
        List<TrainEntity> trainEntities = jsonAdapter.getObjects(FILE_PATH, TrainEntity[].class);
        Iterator<TrainEntity> iterator = trainEntities.iterator();
        while (iterator.hasNext()) {
            TrainEntity entity = iterator.next();
            if (entity.getId().equals(trainId)) {
                entity.setLuggageWagonIds(Arrays.copyOf(entity.getLuggageWagonIds(), entity.getLuggageWagonIds().length + 1));
                entity.getLuggageWagonIds()[entity.getLuggageWagonIds().length - 1] = containerWagon.getId();

                return jsonAdapter.writeObjects(FILE_PATH, trainEntities);
            }
        }
        return false;
    }

    /**
     * Obtiene una lista de trenes por sus IDs.
     *
     * @param ids IDs de los trenes.
     * @return Lista de trenes.
     */
    public LinkedList<Train> getTrainsByIds(Array<String> ids) {
        List<TrainEntity> trainEntities = jsonAdapter.getObjects(FILE_PATH, TrainEntity[].class);
        LinkedList<Train> trains = new LinkedList<>();
        Iterator<String> idIterator = ids.iterator();
        while (idIterator.hasNext()) {
            String id = idIterator.next();
            Iterator<TrainEntity> iterator = trainEntities.iterator();
            while (iterator.hasNext()) {
                TrainEntity entity = iterator.next();
                if (entity.getId().equals(id)) {
                    trains.add(mapToTrain(entity));
                    break;
                }
            }
        }
        return trains;
    }

    /**
     * Elimina un tren del archivo JSON.
     *
     * @param id ID del tren a eliminar.
     * @return Verdadero si el tren se eliminó correctamente, falso en caso contrario.
     */
    public boolean deleteTrain(String id) {
        List<TrainEntity> trainEntities = jsonAdapter.getObjects(FILE_PATH, TrainEntity[].class);
        Iterator<TrainEntity> iterator = trainEntities.iterator();
        while (iterator.hasNext()) {
            TrainEntity trainEntity = iterator.next();
            if (trainEntity.getId().equals(id)) {
                trainEntities.remove(trainEntity);
                return jsonAdapter.writeObjects(FILE_PATH, trainEntities);
            }
        }

        return false; // Train not found
    }

}
