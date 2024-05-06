package patitotrains.model.repository;

import patitotrains.model.domain.Passenger;
import patitotrains.model.domain.PassengerWagon;
import patitotrains.model.repository.entity.PassengerEntity;
import patitotrains.model.repository.entity.PassengerWagonEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;

import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

import java.io.Serializable;

/**
 * Repositorio para vagones de pasajeros.
 */
public class PassengerWagonRepository implements Serializable {
    private static final String FILE_PATH = "PassengerWagon.Json";
    private final FileJsonAdapter<PassengerWagonEntity> jsonAdapter;

    public PassengerWagonRepository() {
        this.jsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Obtiene todos los vagones de pasajeros.
     *
     * @return Lista de vagones de pasajeros.
     */
    public List<PassengerWagon> getAllPassengerWagons() {
        List<PassengerWagonEntity> passengerWagonEntities = jsonAdapter.getObjects(FILE_PATH, PassengerWagonEntity[].class);
        List<PassengerWagon> passengerWagons = new LinkedList<>();

        Iterator<PassengerWagonEntity> iterator = passengerWagonEntities.iterator();
        while (iterator.hasNext()) {
            passengerWagons.add(mapToPassengerWagon(iterator.next()));
        }

        return passengerWagons;
    }

    /**
     * Guarda un vagon de pasajeros en el archivo JSON.
     *
     * @param passengerWagon Vagon de pasajeros.
     * @return Verdadero si el vagon de pasajeros se guardó correctamente, falso en caso contrario.
     */
    public boolean savePassengerWagon(PassengerWagon passengerWagon) {
        List<PassengerWagonEntity> passengerWagonEntities = jsonAdapter.getObjects(FILE_PATH, PassengerWagonEntity[].class);
        PassengerWagonEntity entity = mapToPassengerWagonEntity(passengerWagon);
        passengerWagonEntities.add(entity);
        return jsonAdapter.writeObjects(FILE_PATH, passengerWagonEntities);
    }

    /**
     * Actualiza un vagon de pasajeros en el archivo JSON.
     *
     * @param passengerWagon Vagon de pasajeros.
     * @return Verdadero si el vagon de pasajeros se actualizó correctamente, falso en caso contrario.
     */
    public boolean updatePassengerWagon(PassengerWagon passengerWagon) {
        List<PassengerWagonEntity> passengerWagonEntities = jsonAdapter.getObjects(FILE_PATH, PassengerWagonEntity[].class);
        PassengerWagonEntity entity = mapToPassengerWagonEntity(passengerWagon);
        Iterator<PassengerWagonEntity> iterator = passengerWagonEntities.iterator();
        while (iterator.hasNext()) {
            PassengerWagonEntity passengerWagonEntity = iterator.next();
            if (passengerWagonEntity.getId().equals(passengerWagon.getId())) {
                passengerWagonEntities.remove(passengerWagonEntity);
                passengerWagonEntities.add(entity);
                return jsonAdapter.writeObjects(FILE_PATH, passengerWagonEntities);
            }
        }
        return false; // Passenger wagon not found
    }

    /**
     * Elimina un vagon de pasajeros del archivo JSON.
     *
     * @param id ID del vagon de pasajeros a eliminar.
     * @return Verdadero si el vagon de pasajeros se eliminó correctamente, falso en caso contrario.
     */
    public boolean deletePassengerWagon(String id) {
        List<PassengerWagonEntity> passengerWagonEntities = jsonAdapter.getObjects(FILE_PATH, PassengerWagonEntity[].class);
        Iterator<PassengerWagonEntity> iterator = passengerWagonEntities.iterator();
        while (iterator.hasNext()) {
            PassengerWagonEntity passengerWagonEntity = iterator.next();
            if (passengerWagonEntity.getId().equals(id)) {
                passengerWagonEntities.remove(passengerWagonEntity);
                return jsonAdapter.writeObjects(FILE_PATH, passengerWagonEntities);
            }
        }

        return false; // Passenger wagon not found
    }

    /**
     * Obtiene un vagon de pasajeros por su ID.
     *
     * @param id ID del vagon de pasajeros.
     * @return Vagon de pasajeros.
     */
    public PassengerWagon getPassengerWagonById(String id) {
        List<PassengerWagonEntity> passengerWagonEntities = jsonAdapter.getObjects(FILE_PATH, PassengerWagonEntity[].class);
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
     * Mapea una entidad de vagon de pasajeros a un objeto de dominio de vagon de pasajeros.
     *
     * @param entity Entidad de vagon de pasajeros.
     * @return Objeto de dominio de vagon de pasajeros.
     */
    private PassengerWagon mapToPassengerWagon(PassengerWagonEntity entity) {
        PassengerWagon passengerWagon = new PassengerWagon(entity.getId(), entity.isFirstWagon());

        // Convertir los arrays de PassengerEntity[] a LinkedList<Passenger>
        LinkedList<Passenger> premiumPassengers = mapToPassengerList(entity.getPremiumPassengers());
        LinkedList<Passenger> executivePassengers = mapToPassengerList(entity.getExecutivePassengers());
        LinkedList<Passenger> standardPassengers = mapToPassengerList(entity.getStandardPassengers());

        passengerWagon.setPremiumPassengers(premiumPassengers);
        passengerWagon.setExecutivePassengers(executivePassengers);
        passengerWagon.setStandardPassengers(standardPassengers);

        return passengerWagon;
    }



    /**
     * Mapea un array de entidades de pasajeros a una lista de pasajeros.
     *
     * @param entityArray Array de entidades de pasajeros.
     * @return Lista de pasajeros.
     */
    private LinkedList<Passenger> mapToPassengerList(PassengerEntity[] entityArray) {
        LinkedList<Passenger> passengerList = new LinkedList<>();
        for (PassengerEntity entity : entityArray) {
            passengerList.add(mapToPassenger(entity));
        }
        return passengerList;
    }



    /**
     * Mapea una entidad de pasajero a un objeto de dominio de pasajero.
     *
     * @param entity Entidad de pasajero.
     * @return Objeto de dominio de pasajero.
     */
    private Passenger mapToPassenger(PassengerEntity entity) {
        return new Passenger(entity.getNames(), entity.getLastNames(), entity.getNumbers(), entity.getId(), entity.getIdType(), entity.getAddress(), entity.getContactPerson());
    }

    /**
     * Mapea un objeto de dominio de vagon de pasajeros a una entidad de vagon de pasajeros.
     *
     * @param passengerWagon Objeto de dominio de vagon de pasajeros.
     * @return Entidad de vagon de pasajeros.
     */
    private PassengerWagonEntity mapToPassengerWagonEntity(PassengerWagon passengerWagon) {
        // Convertir las listas de pasajeros a arrays de PassengerEntity
        LinkedList<Passenger> premiumPassengers = passengerWagon.getPremiumPassengers();
        LinkedList<Passenger> executivePassengers = passengerWagon.getExecutivePassengers();
        LinkedList<Passenger> standardPassengers = passengerWagon.getStandardPassengers();

        PassengerEntity[] premiumPassengersArray = convertPassengerListToArray(premiumPassengers);
        PassengerEntity[] executivePassengersArray = convertPassengerListToArray(executivePassengers);
        PassengerEntity[] standardPassengersArray = convertPassengerListToArray(standardPassengers);

        return new PassengerWagonEntity(passengerWagon.getId(), passengerWagon.isFirstWagon(), premiumPassengersArray, executivePassengersArray, standardPassengersArray);
    }

    /**
     * Convierte una lista de pasajeros a un array de entidades de pasajeros.
     *
     * @param passengerList Lista de pasajeros.
     * @return Array de entidades de pasajeros.
     */
    private PassengerEntity[] convertPassengerListToArray(LinkedList<Passenger> passengerList) {
        PassengerEntity[] array = new PassengerEntity[passengerList.size()];
        int i = 0;
        Iterator<Passenger> iterator = passengerList.iterator();
        while (iterator.hasNext()) {
            array[i++] = mapToPassengerEntity(iterator.next());
        }
        return array;
    }



    /**
     * Mapea una lista de objetos de dominio de pasajeros a una lista de entidades de pasajeros.
     *
     * @param passengerList Lista de objetos de dominio de pasajeros.
     * @return Lista de entidades de pasajeros.
     */
    private LinkedList<PassengerEntity> mapToPassengerEntityList(LinkedList<Passenger> passengerList) {
        LinkedList<PassengerEntity> entityList = new LinkedList<>();
        Iterator<Passenger> iterator = passengerList.iterator();
        while (iterator.hasNext()) {
            entityList.add(mapToPassengerEntity(iterator.next()));
        }
        return entityList;
    }


    /**
     * Mapea un objeto de dominio de pasajero a una entidad de pasajero.
     *
     * @param passenger Objeto de dominio de pasajero.
     * @return Entidad de pasajero.
     */
    private PassengerEntity mapToPassengerEntity(Passenger passenger) {
        return new PassengerEntity(passenger.getNames(), passenger.getLastNames(), passenger.getNumbers(), passenger.getId(), passenger.getIdType(), passenger.getAddress(), passenger.getContactPerson());
    }

    /**
     * Elimina un pasajero de un vagon de pasajeros.
     *
     * @param idPassenger ID del pasajero a eliminar.
     * @param idWagon ID del vagon de pasajeros.
     * @return Verdadero si el pasajero se eliminó correctamente, falso en caso contrario.
     */
    public boolean deletePassengerFromPassengerWagon(String idPassenger, String idWagon) {
        List<PassengerWagonEntity> passengerWagonEntities = jsonAdapter.getObjects(FILE_PATH, PassengerWagonEntity[].class);
        Iterator<PassengerWagonEntity> iterator = passengerWagonEntities.iterator();
        while (iterator.hasNext()) {
            PassengerWagonEntity passengerWagonEntity = iterator.next();
            if (passengerWagonEntity.getId().equals(idWagon)) {
                PassengerEntity[] premiumPassengers = passengerWagonEntity.getPremiumPassengers();
                PassengerEntity[] executivePassengers = passengerWagonEntity.getExecutivePassengers();
                PassengerEntity[] standardPassengers = passengerWagonEntity.getStandardPassengers();
                if (deletePassengerFromArray(idPassenger, premiumPassengers) ||
                        deletePassengerFromArray(idPassenger, executivePassengers) ||
                        deletePassengerFromArray(idPassenger, standardPassengers)) {
                    return jsonAdapter.writeObjects(FILE_PATH, passengerWagonEntities);
                }
            }
        }
        return false; // Passenger not found
    }

    /**
     * Elimina un pasajero de un array de pasajeros.
     *
     * @param idPassenger ID del pasajero a eliminar.
     * @param passengers Array de pasajeros.
     * @return Verdadero si el pasajero se eliminó correctamente, falso en caso contrario.
     */
    private boolean deletePassengerFromArray(String idPassenger, PassengerEntity[] passengers) {
        int indexToRemove = -1;
        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i].getId().equals(idPassenger)) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove != -1) {
            // Shift elements to remove the null element
            for (int i = indexToRemove; i < passengers.length - 1; i++) {
                passengers[i] = passengers[i + 1];
            }
            // Set the last element to null
            passengers[passengers.length - 1] = null;
            return true;
        }
        return false;
    }


    /**
     * Obtiene todos los pasajeros de un vagon de pasajeros.
     *
     * @param idWagon ID del vagon de pasajeros.
     * @return Lista de pasajeros.
     */
    public List<Passenger> getAllPassengersFromPassengerWagon(String idWagon) {
        List<PassengerWagonEntity> passengerWagonEntities = jsonAdapter.getObjects(FILE_PATH, PassengerWagonEntity[].class);
        Iterator<PassengerWagonEntity> iterator = passengerWagonEntities.iterator();
        while (iterator.hasNext()) {
            PassengerWagonEntity passengerWagonEntity = iterator.next();
            if (passengerWagonEntity.getId().equals(idWagon)) {
                PassengerEntity[] premiumPassengers = passengerWagonEntity.getPremiumPassengers();
                PassengerEntity[] executivePassengers = passengerWagonEntity.getExecutivePassengers();
                PassengerEntity[] standardPassengers = passengerWagonEntity.getStandardPassengers();
                LinkedList<Passenger> passengers = new LinkedList<>();
                for (PassengerEntity passengerEntity : premiumPassengers) {
                    passengers.add(mapToPassenger(passengerEntity));
                }
                for (PassengerEntity passengerEntity : executivePassengers) {
                    passengers.add(mapToPassenger(passengerEntity));
                }
                for (PassengerEntity passengerEntity : standardPassengers) {
                    passengers.add(mapToPassenger(passengerEntity));
                }
                return passengers;
            }
        }
        return null;
    }

    /**
     * Obtiene todos los pasajeros de una categoría de un vagon de pasajeros.
     *
     * @param idWagon ID del vagon de pasajeros.
     * @param category Categoría de pasajeros.
     * @return Lista de pasajeros.
     */
    public List<Passenger> getAllPassengersFromPassengerWagonCategory(String idWagon, String category) {
        List<PassengerWagonEntity> passengerWagonEntities = jsonAdapter.getObjects(FILE_PATH, PassengerWagonEntity[].class);
        Iterator<PassengerWagonEntity> iterator = passengerWagonEntities.iterator();
        while (iterator.hasNext()) {
            PassengerWagonEntity passengerWagonEntity = iterator.next();
            if (passengerWagonEntity.getId().equals(idWagon)) {
                PassengerEntity[] passengers = null;
                switch (category) {
                    case "Premium":
                        passengers = passengerWagonEntity.getPremiumPassengers();
                        break;
                    case "Ejecutivo":
                        passengers = passengerWagonEntity.getExecutivePassengers();
                        break;
                    case "Estandar":
                        passengers = passengerWagonEntity.getStandardPassengers();
                        break;
                }
                LinkedList<Passenger> passengerList = new LinkedList<>();
                for (PassengerEntity passengerEntity : passengers) {
                    passengerList.add(mapToPassenger(passengerEntity));
                }
                return passengerList;
            }
        }
        return null;
    }

    /**
     * Agrega un pasajero a un vagon de pasajeros.
     *
     * @param idWagon ID del vagon de pasajeros.
     * @param passenger Pasajero.
     * @param category Categoría de pasajeros.
     * @return Verdadero si el pasajero se agregó correctamente, falso en caso contrario.
     */
    public List<PassengerWagon> getPassengerWagonsByIds(Array<String> ids) {
        List<PassengerWagonEntity> passengerWagonEntities = jsonAdapter.getObjects(FILE_PATH, PassengerWagonEntity[].class);
        List<PassengerWagon> passengerWagons = new LinkedList<>();
        Iterator<String> iterator = ids.iterator();
        while (iterator.hasNext()) {
            String id = iterator.next();
            Iterator<PassengerWagonEntity> passengerWagonIterator = passengerWagonEntities.iterator();
            while (passengerWagonIterator.hasNext()) {
                PassengerWagonEntity passengerWagonEntity = passengerWagonIterator.next();
                if (passengerWagonEntity.getId().equals(id)) {
                    passengerWagons.add(mapToPassengerWagon(passengerWagonEntity));
                }
            }
        }
        return passengerWagons;
    }




}
