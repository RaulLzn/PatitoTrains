package patitotrains.model.repository;

import java.io.Serializable;

public class ContactPersonEntity implements Serializable {
    String idContactPerson;
    String names;
    String lastNames;
    String phones;

    /**
     * Constructor de la clase
     */
    public ContactPersonEntity(String idContactPerson, String names, String lastNames, String phones) {
        this.names = names;
        this.lastNames = lastNames;
        this.phones = phones;
    }

}
