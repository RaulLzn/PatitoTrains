package patitotrains.controller.domain;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private AbstractPerson person;

    //constructor con parametros
    public User(String username, String password, AbstractPerson person) {
        this.username = username;
        this.password = password;
        this.person = person;
    }

    //Constructor vacio
    public User() {
        this.username = "";
        this.password = "";
        this.person = AbstractPerson.getEmptyPerson();
    }
}
