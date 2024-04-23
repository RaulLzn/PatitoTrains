package patitotrains.model.repository.entity;

import patitotrains.model.domain.User;
import raul.Model.array.Array;

import java.io.Serializable;

public class UserEntity implements Serializable {
    private String names;
    private String lastNames;
    private Array<String> numbers;
    private boolean disabled;
    private String id;
    private String password;
    private String userName;

    public UserEntity(String names, String lastNames, Array<String> numbers, boolean disabled, String id, String userName, String password) {
        this.names = names;
        this.lastNames = lastNames;
        this.numbers = numbers;
        this.disabled = disabled;
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    // Constructor para la conversión de un objeto User a UserEntity
    public UserEntity(User user) {
        this.names = user.getNames();
        this.lastNames = user.getLastNames();
        this.numbers = user.getNumbers();
        this.disabled = user.isDisabled();
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
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

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public Array<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(Array<String> numbers) {
        this.numbers = numbers;
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
        return "UserEntity{" +
                "names='" + names + '\'' +
                ", lastNames='" + lastNames + '\'' +
                ", numbers=" + numbers +
                ", disabled=" + disabled +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
