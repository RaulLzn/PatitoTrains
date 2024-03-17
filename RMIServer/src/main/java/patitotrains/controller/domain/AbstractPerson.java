package patitotrains.controller.domain;

import raul.Model.array.Array;

import java.io.Serializable;

public abstract class AbstractPerson implements Serializable {
    protected String names;
    protected String lastNames;
    protected Array<String> phones;

    //constructor con parametros
    public AbstractPerson(String names, String lastNames, Array<String> phones) {
        this.names = names;
        this.lastNames = lastNames;
        this.phones = phones;
    }

    //Constructor vacio
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

    //Devolver constructor vacio
    public static AbstractPerson getEmptyPerson() {
        return new Passenger();
    }

}
