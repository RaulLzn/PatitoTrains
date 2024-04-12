package patitotrains.model.repository;

import patitotrains.model.domain.ContactPerson;
import patitotrains.model.domain.IdType;
import patitotrains.model.domain.Passenger;
import patitotrains.shared.jsonAdapter.MySQLAdapter;

import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

import java.io.Serializable;



/**
 * Clase que se encarga de la persistencia de los pasajeros
 */
public class PassengerRepository implements Serializable {
    /**
     * Adaptador de MySQL
     */
    private final MySQLAdapter<PassengerEntity> mySQLAdapter;
    /**
     * Nombre de la tabla en la base de datos
     */
    private final String tableName;
    /**
     * Arreglo de entidades de pasajeros
     */
    private Array<PassengerEntity> passengersEntities;
    /**
     * Repositorio de personas de contacto
     */
    private final ContactPersonRepository contactPersonRepository = new ContactPersonRepository();


    /**
     * Constructor de la clase
     */
    public PassengerRepository() {
        this.tableName = "Passenger";
        this.mySQLAdapter = MySQLAdapter.getInstance();
        LoadPassengers();
    }

    /**
     * Método que carga los pasajeros de la base de datos
     */
    private void LoadPassengers(){
        passengersEntities = new Array<>(mySQLAdapter.getObjects(tableName, PassengerEntity[].class));
    }

    /**
     * Método que retorna un pasajero por su id
     * @param idPassenger id del pasajero
     * @return Pasajero
     */
    public Passenger gerPassengerById(String idPassenger){
        Iterator<PassengerEntity> iterator = passengersEntities.iterator();
        while (iterator.hasNext()){
            PassengerEntity passengerEntity = iterator.next();
            if (passengerEntity.idPassenger.equals(idPassenger)){
                // Obtener la persona de contacto del pasajero
                ContactPerson contactPerson = contactPersonRepository.getPassengerContactByIdPassenger(passengerEntity.idPassenger);

                // Separar la cadena de números de teléfono y convertirla en un array de números
                String[] phoneNumbersStr = passengerEntity.phones.split(",");
                Array<String> phoneNumbers = new Array<>(phoneNumbersStr.length);
                for (String phoneNumberStr : phoneNumbersStr) {
                    phoneNumbers.add(phoneNumberStr.trim());
                }

                // Crear el objeto Passenger con los datos correctos
                return new Passenger(passengerEntity.names, passengerEntity.lastNames, phoneNumbers, passengerEntity.idPassenger, IdType.valueOf(passengerEntity.idType), passengerEntity.address, contactPerson);
            }
        }
        return Passenger.getEmptyPassenger();
    }

    /**
     * Método que retorna una lista de pasajeros
     * @return Lista de pasajeros
     */
    public List<Passenger> getPassengers(){
        List<Passenger> passengers = new LinkedList<>();
        Iterator<PassengerEntity> iterator = passengersEntities.iterator();
        while (iterator.hasNext()){
            PassengerEntity passengerEntity = iterator.next();

            ContactPerson contactPerson = contactPersonRepository.getPassengerContactByIdPassenger(passengerEntity.idPassenger);

            // Separar la cadena de números de teléfono y convertirla en un array de números
            String[] phoneNumbersStr = passengerEntity.phones.split(",");
            Array<String> phones = new Array<>(phoneNumbersStr.length);
            for (String phoneNumberStr : phoneNumbersStr) {
                phones.add(phoneNumberStr.trim());
            }

            // Crear el objeto Passenger con los datos correctos
            passengers.add(new Passenger(passengerEntity.names, passengerEntity.lastNames, phones, passengerEntity.idPassenger, IdType.valueOf(passengerEntity.idType), passengerEntity.address, contactPerson));
        }
        return passengers;
    }

    /**
     * Método para agregar un pasajero a la base de datos junto con su persona de contacto asociada
     * @param newPassenger Nuevo pasajero a agregar
     * @param contactPerson Persona de contacto asociada al pasajero
     * @return Verdadero si la operación fue exitosa, falso en caso contrario
     */
    public boolean addPassengerWithContact(Passenger newPassenger, ContactPerson contactPerson) {
        int currentSize = passengersEntities.size();

        PassengerEntity[] newArray = new PassengerEntity[currentSize + 1];

        for (int i = 0; i < currentSize; i++) {
            newArray[i] = passengersEntities.get(i);
        }

        newArray[currentSize] = new PassengerEntity(
                newPassenger.getIdPassenger(),
                newPassenger.getNames(),
                newPassenger.getLastNames(),
                newPassenger.getIdType().toString(),
                newPassenger.getAddress(),
                newPassenger.getPhonesAsString()
        );

        // Escribir el nuevo pasajero en la base de datos
        boolean passengerAdded = mySQLAdapter.writeObjects(tableName, newArray);

        // Si el pasajero se agregó correctamente, agregar también la persona de contacto
        if (passengerAdded) {
            return contactPersonRepository.addContactPerson(contactPerson);
        }

        return false;
    }

    /**
     * Método para agregar pasajeros a la base de datos junto con sus personas de contacto asociadas
     * @param newPassengers Lista enlazada de pasajeros a agregar
     * @param contactPersons Lista enlazada de personas de contacto asociadas a los pasajeros
     * @return Verdadero si la operación fue exitosa, falso en caso contrario
     */
    public boolean addPassengersWithContacts(LinkedList<Passenger> newPassengers, LinkedList<ContactPerson> contactPersons) {
        // Verificar que las listas tengan el mismo tamaño
        if (newPassengers.size() != contactPersons.size()) {
            return false;
        }

        LinkedList<PassengerEntity> tempPassengerList = new LinkedList<>();

        Iterator<Passenger> passengerIterator = newPassengers.iterator();
        Iterator<ContactPerson> contactIterator = contactPersons.iterator();

        // Recorrer ambas listas al mismo tiempo
        while (passengerIterator.hasNext() && contactIterator.hasNext()) {
            Passenger passenger = passengerIterator.next();
            ContactPerson contactPerson = contactIterator.next();

            // Agregar el pasajero a la lista temporal
            tempPassengerList.add(new PassengerEntity(
                    passenger.getIdPassenger(),
                    passenger.getNames(),
                    passenger.getLastNames(),
                    passenger.getPhonesAsString(),
                    passenger.getIdType().toString(),
                    passenger.getAddress()
            ));

            // Agregar la persona de contacto a la base de datos
            boolean contactAdded = contactPersonRepository.addContactPerson(contactPerson);
            if (!contactAdded) {
                return false;
            }
        }

        PassengerEntity[] combinedPassengerArray = tempPassengerList.toArray();

        // Escribir todos los objetos (pasajeros y personas de contacto) en la base de datos
        return mySQLAdapter.writeObjects(tableName, combinedPassengerArray);
    }



}
