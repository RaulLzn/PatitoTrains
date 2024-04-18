package patitotrains.model.repository.entity;

import patitotrains.model.domain.Passenger;
import patitotrains.model.domain.types.IdType;
import patitotrains.model.repository.entity.typesEntity.IdTypeEntity;
import raul.Model.array.Array;
import raul.Model.util.Iterator.Iterator;

/**
 * Clase PassengerEntity
 */
public class PassengerEntity {
    private String id;
    private String names;
    private String lastNames;
    private Array<String> numbers;
    private String address;
    private IdTypeEntity idType; // Cambiado a IdTypeEntity
    private String contactPersonId;

    /**
     * Constructor de la clase PassengerEntity
     * @param id
     * @param names
     * @param lastNames
     * @param numbers
     * @param address
     * @param idType
     * @param contactPersonId
     */
    public PassengerEntity(String id, String names, String lastNames, Array<String> numbers, String address, IdTypeEntity idType, String contactPersonId) {
        this.id = id;
        this.names = names;
        this.lastNames = lastNames;
        this.numbers = numbers;
        this.address = address;
        this.idType = idType; // Actualizado a IdTypeEntity
        this.contactPersonId = contactPersonId;
    }

    /**
     * Constructor de la clase PassengerEntity
     * @param passenger
     */
    public PassengerEntity(Passenger passenger) {
        this.id = passenger.getId();
        this.names = passenger.getNames();
        this.lastNames = passenger.getLastNames();
        this.address = passenger.getAddress();
        this.contactPersonId = passenger.getContactPerson().getId();

        // Inicializar la lista de números correctamente
        if (passenger.getNumbers() != null) {
            this.numbers = new Array<>(2);
            Iterator<String> iterator = passenger.getNumbers().iterator();
            while (iterator.hasNext()) {
                this.numbers.add(iterator.next());
            }
        } else {
            this.numbers = new Array<>(2);
        }

        IdType idType = passenger.getIdType();
        this.idType = new IdTypeEntity(idType.getId(), idType.getDescription());
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(String contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IdTypeEntity getIdType() {
        return idType;
    }

    public void setIdType(IdTypeEntity idType) {
        this.idType = idType;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public Array<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(Array<String> numbers) {
        this.numbers = numbers;
    }
}
