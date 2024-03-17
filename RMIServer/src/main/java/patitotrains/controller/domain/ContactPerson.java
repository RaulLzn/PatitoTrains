package patitotrains.controller.domain;

import raul.Model.array.Array;

public class ContactPerson extends AbstractPerson{
    private String id;

    //constructor con parametros
    public ContactPerson(String names, String lastNames, Array<String> phones, String id) {
        super(names, lastNames, phones);
        this.id = id;
    }

    //Constructor vacio
    public ContactPerson() {
        super();
        this.id = "";
    }

    //Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //Devolver constructor vacio
    public static ContactPerson getEmptyContactPerson() {
        return new ContactPerson();
    }
}
