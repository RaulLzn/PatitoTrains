package patitotrains.model.domain;

import patitotrains.model.domain.types.IdType;
import raul.Model.array.Array;

public class Passenger extends AbstractPerson {
    private String id;
    private IdType idType;
    private String address;
    private ContactPerson contactPerson;

    public Passenger(String names, String lastNames, Array<String> numbers, String id, IdType idType, String address, ContactPerson contactPerson) {
        super(names, lastNames, numbers);
        this.id = id;
        this.idType = idType;
        this.address = address;
        this.contactPerson = contactPerson;
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

    @Override
    public String toString() {
        return "Passenger{" +
                "id='" + id + '\'' +
                ", idType=" + idType +
                ", contactPerson=" + contactPerson +
                ", lastNames='" + lastNames + '\'' +
                ", names='" + names + '\'' +
                ", numbers=" + numbers +
                '}';
    }
}
