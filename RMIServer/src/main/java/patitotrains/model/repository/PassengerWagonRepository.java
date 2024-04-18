package patitotrains.model.repository;

import patitotrains.model.domain.PassengerWagon;
import patitotrains.model.repository.entity.PassengerWagonEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

/**
 * Repositorio de vagones de pasajeros.
 */
public class PassengerWagonRepository {

    private final String PASSENGER_WAGON_DATA_FILE = "RMIServer/src/main/java/database/PassengerWagon.Json"; // Nombre del archivo JSON
    private final FileJsonAdapter<PassengerWagonEntity> fileJsonAdapter; // Adaptador de archivos JSON

    /**
     * Constructor de la clase.
     */
    public PassengerWagonRepository() {
        this.fileJsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Obtiene una lista de todos los vagones de pasajeros.
     *
     * @return Lista de vagones de pasajeros.
     */
    public List<PassengerWagon> getAllPassengerWagons() {
        List<PassengerWagonEntity> passengerWagonEntities = fileJsonAdapter.getObjects(PASSENGER_WAGON_DATA_FILE, PassengerWagonEntity[].class);
        List<PassengerWagon> passengerWagons = new LinkedList<>();

        Iterator<PassengerWagonEntity> iterator = passengerWagonEntities.iterator();
        while (iterator.hasNext()) {
            passengerWagons.add(mapToPassengerWagon(iterator.next()));
        }

        return passengerWagons;
    }

    /**
     * Guarda un vagón de pasajeros en el archivo JSON.
     *
     * @param passengerWagon Vagón de pasajeros.
     * @return Verdadero si el vagón de pasajeros se guardó correctamente, falso en caso contrario.
     */
    public boolean savePassengerWagon(PassengerWagon passengerWagon) {
        List<PassengerWagonEntity> passengerWagonEntities = fileJsonAdapter.getObjects(PASSENGER_WAGON_DATA_FILE, PassengerWagonEntity[].class);
        PassengerWagonEntity entity = mapToPassengerWagonEntity(passengerWagon);
        passengerWagonEntities.add(entity);

        return fileJsonAdapter.writeObjects(PASSENGER_WAGON_DATA_FILE, passengerWagonEntities);
    }

    /**
     * Mapea un objeto PassengerWagonEntity a un objeto PassengerWagon.
     *
     * @param entity Objeto PassengerWagonEntity.
     * @return Objeto PassengerWagon.
     */
    private PassengerWagon mapToPassengerWagon(PassengerWagonEntity entity) {
        return new PassengerWagon(entity.getId(), entity.isFirstWagon());
    }

    /**
     * Mapea un objeto PassengerWagon a un objeto PassengerWagonEntity.
     *
     * @param passengerWagon Objeto PassengerWagon.
     * @return Objeto PassengerWagonEntity.
     */
    private PassengerWagonEntity mapToPassengerWagonEntity(PassengerWagon passengerWagon) {
        return new PassengerWagonEntity(passengerWagon.getId(), passengerWagon.isFirstWagon());
    }

    /**
     * Obtiene un vagón de pasajeros por su identificación.
     *
     * @param id Identificación del vagón de pasajeros.
     * @return Vagón de pasajeros.
     */
    public PassengerWagon getPassengerWagonById(String id) {
        List<PassengerWagonEntity> passengerWagonEntities = fileJsonAdapter.getObjects(PASSENGER_WAGON_DATA_FILE, PassengerWagonEntity[].class);
        Iterator<PassengerWagonEntity> iterator = passengerWagonEntities.iterator();
        while (iterator.hasNext()) {
            PassengerWagonEntity passengerWagonEntity = iterator.next();
            if (passengerWagonEntity.getId().equals(id)) {
                return mapToPassengerWagon(passengerWagonEntity);
            }
        }
        return null;
    }

    /**
     * Elimina un vagón de pasajeros por su identificación.
     *
     * @param id Identificación del vagón de pasajeros.
     * @return Verdadero si el vagón de pasajeros se eliminó correctamente, falso en caso contrario.
     */
    public boolean deletePassengerWagon(String id) {
        List<PassengerWagonEntity> passengerWagonEntities = fileJsonAdapter.getObjects(PASSENGER_WAGON_DATA_FILE, PassengerWagonEntity[].class);
        Iterator<PassengerWagonEntity> iterator = passengerWagonEntities.iterator();
        while (iterator.hasNext()) {
            PassengerWagonEntity passengerWagonEntity = iterator.next();
            if (passengerWagonEntity.getId().equals(id)) {
                passengerWagonEntities.remove(passengerWagonEntity);
                return fileJsonAdapter.writeObjects(PASSENGER_WAGON_DATA_FILE, passengerWagonEntities);
            }
        }
        return false;
    }
}
