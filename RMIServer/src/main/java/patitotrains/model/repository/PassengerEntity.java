package patitotrains.model.repository;

import java.io.Serializable;

/**
 * Clase que representa la entidad de pasajero
 */
public class PassengerEntity implements Serializable {
    String idPassenger;
    String idType;
    String address;
    String names;
    String lastNames;
    String phones;

    /**
     * Constructor de la clase
     */
    public PassengerEntity(String idPassenger, String idType, String address, String names, String lastNames, String phones) {
        this.idPassenger = idPassenger;
        this.idType = idType;
        this.address = address;
        this.names = names;
        this.lastNames = lastNames;
        this.phones = phones;
    }


}
