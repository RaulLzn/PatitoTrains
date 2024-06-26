package patitotrains.model.domain;

import raul.Model.array.Array;

import java.io.Serializable;

public class User extends AbstractPerson implements Serializable {
    private String id;
    private String userName;
    private String password;
    private boolean disabled;

    public User(String names, String lastNames, Array<String> numbers, boolean disabled, String id, String userName, String password) {
        super(names, lastNames, numbers);
        this.disabled = disabled;
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "disabled=" + disabled +
                ", id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", lastNames='" + lastNames + '\'' +
                ", names='" + names + '\'' +
                ", numbers=" + numbers +
                '}';
    }
}
