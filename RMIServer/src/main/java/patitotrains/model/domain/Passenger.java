package patitotrains.model.domain;

import patitotrains.model.domain.types.IdType;
import raul.Model.array.Array;

import java.io.Serializable;

/**
 * Clase que representa un pasajero.
 */
public class Passenger extends AbstractPerson implements Serializable {
    private String id;
    private IdType idType;
    private String address;
    private ContactPerson contactPerson;

    /**
     * Constructor de la clase.
     *
     * @param names         Nombres de la persona.
     * @param lastNames     Apellidos de la persona.
     * @param numbers       Números de contacto de la persona.
     * @param id            ID de la persona.
     * @param idType        Tipo de identificación.
     * @param address       Dirección de la persona.
     * @param contactPerson Persona de contacto.
     */
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
                "names='" + names + '\'' +
                ", lastNames='" + lastNames + '\'' +
                ", numbers=" + numbers + '\'' +
                ", id='" + id + '\'' +
                ", idType=" + idType +
                ", address='" + address + '\'' +
                ", contactPerson=" + contactPerson +
                '}';
    }
}
