package  upb.ticketpurchase.model.domain;

import com.edu.upb.array.Array;

public class User extends AbstractPerson {
    private String id;
    private String userName;
    private String password;
    private boolean disabled;


    public User(String names, String lastNames, Array<String> numbers, String id, String userName, String password,
            boolean disabled) {
        super(names, lastNames, numbers);
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.disabled = disabled;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    
}
