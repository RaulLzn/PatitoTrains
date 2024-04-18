package patitotrains.model.repository;

import patitotrains.model.domain.ContactPerson;
import patitotrains.model.domain.Passenger;
import patitotrains.model.domain.types.IdType;
import patitotrains.model.repository.entity.ContactPersonEntity;
import patitotrains.model.repository.entity.PassengerEntity;
import patitotrains.model.repository.entity.typesEntity.IdTypeEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;

import raul.Model.array.Array;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;
import raul.Model.linkedlist.doubly.circular.LinkedList;

/**
 * Repositorio de pasajeros.
 */
public class PassengerRepository {

    private final String PASSENGER_DATA_FILE = "RMIServer/src/main/java/database/Passenger.Json"; // Nombre del archivo JSON
    private final FileJsonAdapter<PassengerEntity> fileJsonAdapter; // Adaptador de archivos JSON

    /**
     * Constructor de la clase.
     */
    public PassengerRepository() {
        this.fileJsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Obtiene una lista de todos los pasajeros.
     *
     * @return Lista de pasajeros.
     */
    public List<Passenger> getAllPassengers() {
        List<PassengerEntity> passengerEntities = fileJsonAdapter.getObjects(PASSENGER_DATA_FILE, PassengerEntity[].class);
        List<Passenger> passengers = new LinkedList<>();

        Iterator<PassengerEntity> iterator = passengerEntities.iterator();
        while (iterator.hasNext()) {
            passengers.add(mapToPassenger(iterator.next()));
        }

        return passengers;
    }

    /**
     * Guarda un pasajero en el archivo JSON.
     *
     * @param passenger Pasajero.
     * @return Verdadero si el pasajero se guardó correctamente, falso en caso contrario.
     */
    public boolean savePassenger(Passenger passenger) {
        List<PassengerEntity> passengerEntities = fileJsonAdapter.getObjects(PASSENGER_DATA_FILE, PassengerEntity[].class);
        PassengerEntity entity = mapToPassengerEntity(passenger);
        passengerEntities.add(entity);

        return fileJsonAdapter.writeObjects(PASSENGER_DATA_FILE, passengerEntities);
    }

    /**
     * Mapea un objeto PassengerEntity a un objeto Passenger.
     *
     * @param entity Objeto PassengerEntity.
     * @return Objeto Passenger.
     */
    private Passenger mapToPassenger(PassengerEntity entity) {
        String numbers = buildNumbersString(entity.getNumbers());

        IdType idType = mapToIdType(entity.getIdType());

        return new Passenger(
                entity.getNames(),
                entity.getLastNames(),
                new Array<>(numbers.split(", ")),
                entity.getId(),
                idType,
                entity.getAddress(),
                null // No se maneja directamente la persona de contacto aquí para evitar una posible recursión infinita
        );
    }

    /**
     * Construye una cadena de números a partir de un arreglo de números.
     *
     * @param numbersArray Arreglo de números.
     * @return Cadena de números.
     */
    private String buildNumbersString(Array<String> numbersArray) {
        StringBuilder numbersBuilder = new StringBuilder();
        Iterator<String> iterator = numbersArray.iterator();
        while (iterator.hasNext()) {
            numbersBuilder.append(iterator.next()).append(", ");
        }
        return numbersBuilder.substring(0, numbersBuilder.length() - 2);
    }

    /**
     * Mapea un objeto IdTypeEntity a un objeto IdType.
     *
     * @param idTypeEntity Objeto IdTypeEntity.
     * @return Objeto IdType.
     */
    private IdType mapToIdType(IdTypeEntity idTypeEntity) {
        return new IdType(idTypeEntity.getId(), idTypeEntity.getDescription());
    }

    /**
     * Mapea un objeto Passenger a un objeto PassengerEntity.
     *
     * @param passenger Objeto Passenger.
     * @return Objeto PassengerEntity.
     */
    private PassengerEntity mapToPassengerEntity(Passenger passenger) {
        // Obtener el tamaño de la colección de números del pasajero
        int numbersSize = passenger.getNumbers().size();
        // Crear un array con el tamaño de la colección de números del pasajero
        Array<String> numbers = new Array<>(numbersSize);
        // Iterar sobre los números del pasajero y agregarlos al array
        Iterator<String> iterator = passenger.getNumbers().iterator();
        while (iterator.hasNext()) {
            numbers.add(iterator.next());
        }

        // Mapear el tipo de identificación del pasajero
        IdTypeEntity idTypeEntity = mapToIdTypeEntity(passenger.getIdType());

        // Crear y devolver la entidad del pasajero
        return new PassengerEntity(
                passenger.getId(),
                passenger.getNames(),
                passenger.getLastNames(),
                numbers,
                passenger.getAddress(),
                idTypeEntity,
                null // No se maneja directamente la persona de contacto aquí para evitar una posible recursión infinita
        );
    }


    /**
     * Mapea un objeto IdType a un objeto IdTypeEntity.
     *
     * @param idType Objeto IdType.
     * @return Objeto IdTypeEntity.
     */
    private IdTypeEntity mapToIdTypeEntity(IdType idType) {
        return new IdTypeEntity(idType.getId(), idType.getDescription());
    }

    /**
     * Obtiene un pasajero por su identificación.
     *
     * @param id Identificación del pasajero.
     * @return Pasajero.
     */
    public Passenger getPassengerById(String id) {
        List<PassengerEntity> passengerEntities = fileJsonAdapter.getObjects(PASSENGER_DATA_FILE, PassengerEntity[].class);
        Iterator<PassengerEntity> iterator = passengerEntities.iterator();
        while (iterator.hasNext()) {
            PassengerEntity passengerEntity = iterator.next();
            if (passengerEntity.getId().equals(id)) {
                return mapToPassenger(passengerEntity);
            }
        }
        return null;
    }

    /**
     * Elimina un pasajero por su identificación.
     *
     * @param id Identificación del pasajero.
     * @return Verdadero si el pasajero se eliminó correctamente, falso en caso contrario.
     */
    public boolean deletePassenger(String id) {
        List<PassengerEntity> passengerEntities = fileJsonAdapter.getObjects(PASSENGER_DATA_FILE, PassengerEntity[].class);
        Iterator<PassengerEntity> iterator = passengerEntities.iterator();
        while (iterator.hasNext()) {
            PassengerEntity passengerEntity = iterator.next();
            if (passengerEntity.getId().equals(id)) {
                passengerEntities.remove(passengerEntity);
                return fileJsonAdapter.writeObjects(PASSENGER_DATA_FILE, passengerEntities);
            }
        }
        return false;
    }
}
