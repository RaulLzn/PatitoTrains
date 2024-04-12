package patitotrains.model.domain;

import raul.Model.array.Array;

import java.io.Serializable;

/**
 * Clase que representa un pasajero
 */
public class Passenger extends AbstractPerson implements Serializable {
    private String idPassenger;
    private IdType idType;
    private String address;
    private String idContactPerson;

    /**
     * Constructor de la clase
     * @param names nombres del pasajero
     * @param lastNames apellidos del pasajero
     * @param phones teléfonos del pasajero
     * @param idPassenger identificación del pasajero
     * @param idType tipo de identificación del pasajero
     * @param address dirección del pasajero
     * @param contactPerson persona de contacto del pasajero
     */
    public Passenger(String names, String lastNames, Array<String> phones, String idPassenger, IdType idType, String address, ContactPerson contactPerson) {
        super(names, lastNames, phones);
        this.idPassenger = idPassenger;
        this.idType = idType;
        this.address = address;
        this.contactPerson = contactPerson;
    }

    /**
     * Constructor vacio
     */
    public Passenger() {
        super();
        this.idPassenger = "";
        this.idType = IdType.NULL;
        this.address = "";
        this.contactPerson = ContactPerson.getEmptyContactPerson();
    }

    //Getters y Setters
    public String getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(String idPassenger) {
        this.idPassenger = idPassenger;
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

    /**
     * Método que retorna un pasajero vacío
     * @return pasajero vacío
     */
    public static Passenger getEmptyPassenger() {
        return new Passenger();
    }
}
