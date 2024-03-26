package patitotrains.model.domain;

import raul.Model.array.Array;

/**
 * Clase que representa una persona de contacto
 */
public class ContactPerson extends AbstractPerson{

    /**
     * Constructor de la clase
     * @param names nombres de la persona
     * @param lastNames apellidos de la persona
     * @param phones teléfonos de la persona
     */
    public ContactPerson(String names, String lastNames, Array<String> phones) {
        super(names, lastNames, phones);
    }

    /**
     * Constructor vacio
     */
    public ContactPerson() {
        super();
    }

    /**
     * Método que retorna una persona de contacto vacía
     * @return persona de contacto vacía
     */
    public static ContactPerson getEmptyContactPerson() {
        return new ContactPerson();
    }
}
