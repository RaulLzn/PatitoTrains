package patitotrains.controller.domain;

import raul.Model.array.Array;

import java.io.Serializable;

public class Passenger extends AbstractPerson implements Serializable {
    private String id;
    private IdType idType;
    private String address;
    private ContactPerson contactPerson;

    //constructor con parametros
    public Passenger(String names, String lastNames, Array<String> phones, String id, IdType idType, String address, ContactPerson contactPerson) {
        super(names, lastNames, phones);
        this.id = id;
        this.idType = idType;
        this.address = address;
        this.contactPerson = contactPerson;
    }

    //Constructor vacio
    public Passenger() {
        super();
        this.id = "";
        this.idType = IdType.NULL;
        this.address = "";
        this.contactPerson = ContactPerson.getEmptyContactPerson();
    }

    //Getters y Setters
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

    //Devolver constructor vacio
    public static Passenger getEmptyPassenger() {
        return new Passenger();
    }
}
