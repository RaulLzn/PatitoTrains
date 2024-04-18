package patitotrains.model.repository.entity;

public class UserEntity {
    private String userName;
    private String password;
    private boolean disabled;
    private String personId; // En lugar de almacenar toda la información del usuario, almacenaremos solo su ID

    public UserEntity(String userName, String password, boolean disabled, String personId) {
        this.userName = userName;
        this.password = password;
        this.disabled = disabled;
        this.personId = personId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
