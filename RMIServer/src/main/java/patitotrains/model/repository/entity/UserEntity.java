package patitotrains.model.repository.entity;

import patitotrains.model.domain.User;
import raul.Model.array.Array;

public class UserEntity {
    private String id;
    private String userName;
    private String password;
    private boolean disabled;
    private String names;
    private String lastNames;
    private Array<String> numbers;

    public UserEntity(String id, String userName, String password, boolean disabled, String names, String lastNames, Array<String> numbers) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.disabled = disabled;
        this.names = names;
        this.lastNames = lastNames;
        this.numbers = numbers;
    }

    // Constructor para la conversión de un objeto User a UserEntity
    public UserEntity(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.disabled = user.isDisabled();
        this.names = user.getNames();
        this.lastNames = user.getLastNames();
        this.numbers = user.getNumbers();
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
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", disabled=" + disabled +
                ", names='" + names + '\'' +
                ", lastNames='" + lastNames + '\'' +
                ", numbers=" + numbers +
                '}';
    }
}
