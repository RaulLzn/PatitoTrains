package upb.administrator.model.domain;

public class User {
    private String userName;
    private String password;
    private boolean disabled;
    private AbstractPerson person;

    public User(String userName, String password, boolean disabled, AbstractPerson person) {
        this.userName = userName;
        this.password = password;
        this.disabled = disabled;
        this.person = person;
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
    public AbstractPerson getPerson() {
        return person;
    }
    public void setPerson(AbstractPerson person) {
        this.person = person;
    }

    
    
}
