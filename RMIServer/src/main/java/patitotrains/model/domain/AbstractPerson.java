package patitotrains.model.domain;

import raul.Model.array.Array;

import java.io.Serializable;

/**
 * Clase abstracta que representa una persona
 */
public abstract class AbstractPerson implements Serializable {
    protected String names;
    protected String lastNames;
    protected Array<String> phones;

    /**
     * Constructor de la clase
     * @param names nombres de la persona
     * @param lastNames apellidos de la persona
     * @param phones teléfonos de la persona
     */
    public AbstractPerson(String names, String lastNames, Array<String> phones) {
        this.names = names;
        this.lastNames = lastNames;
        this.phones = phones;
    }

    /**
     * Constructor vacio
     */
    public AbstractPerson() {
        this.names = "";
        this.lastNames = "";
        this.phones = new Array<>(1);
    }

    //Getters y Setters
    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public Array<String> getPhones() {
        return phones;
    }

    public void setPhones(Array<String> phones) {
        this.phones = phones;
    }

    /**
     * Método que retorna una persona vacía
     * @return persona vacía
     */
    public static AbstractPerson getEmptyPerson() {
        return new Passenger();
    }

    /**
     * Método que retorna los teléfonos de la persona como una cadena
     * @return teléfonos de la persona como una cadena
     */
    public String getPhonesAsString() {
        StringBuilder phonesString = new StringBuilder();
        for (int i = 0; i < phones.size(); i++) {
            phonesString.append(phones.get(i));
            if (i < phones.size() - 1) {
                phonesString.append(", ");
            }
        }
        return phonesString.toString();
    }

}
