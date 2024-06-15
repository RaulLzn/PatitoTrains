package patitotrains.model.repository.entity;

import patitotrains.model.domain.ContactPerson;
import patitotrains.model.domain.Passenger;
import patitotrains.model.domain.types.IdType;
import raul.Model.array.Array;

import java.io.Serializable;

public class PassengerEntity extends AbstractPersonEntity implements Serializable {
    private String id;
    private IdType idType;
    private String address;
    private ContactPerson contactPerson;

    /**
     * Creates a new passenger entity.
     *
     * @param names         the passenger's names
     * @param lastNames     the passenger's last names
     * @param numbers       the passenger's numbers
     * @param id            the passenger's id
     * @param idType        the passenger's id type
     * @param address       the passenger's address
     * @param contactPerson the passenger's contact person
     */
    public PassengerEntity(String names, String lastNames, Array<String> numbers, String id, IdType idType, String address, ContactPerson contactPerson) {
        super(names, lastNames, numbers);
        this.id = id;
        this.idType = idType;
        this.address = address;
        this.contactPerson = contactPerson;
    }

    /**
     * Creates a new passenger entity.
     *
     * @param passenger the passenger domain object
     */
    public PassengerEntity(Passenger passenger) {
        super(passenger.getNames(), passenger.getLastNames(), passenger.getNumbers());
        this.id = passenger.getId();
        this.idType = passenger.getIdType();
        this.address = passenger.getAddress();
        this.contactPerson = passenger.getContactPerson();
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IdType getIdType() {
        return idType;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    /**
     * Converts this entity to a domain object.
     *
     * @return the domain object
     */
    @Override
    public String toString() {
        return "PassengerEntity{" +
                "id='" + id + '\'' +
                ", idType=" + idType +
                ", address='" + address + '\'' +
                ", contactPerson=" + contactPerson +
                ", lastNames='" + getLastNames() + '\'' +
                ", names='" + getNames() + '\'' +
                ", numbers=" + getNumbers() +
                '}';
    }
}
