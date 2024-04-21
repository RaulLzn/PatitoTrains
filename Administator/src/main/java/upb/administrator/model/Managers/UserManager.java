package upb.administrator.model.Managers;
import com.edu.upb.array.Array;
import com.edu.upb.linkedList.doubly.LinkedList;
import com.edu.upb.util.iterator.Iterator;

import upb.administrator.model.domain.User;

import java.util.function.Predicate;

public class UserManager {

    private LinkedList<User> userList;
    
    private int employeeIdCounter;

    public UserManager(){
        userList = new LinkedList<>();

        pullData();

    }

    public void pullData(){
    
            setEmployeeIdCounter(10022);
            Array<String> numbers = new Array<>(5);
            numbers.add("3008673271");
            numbers.add("21023980123");
            numbers.add("123123415");
            numbers.add("30190129301");
    
            
    
            userList.add(new User("Maria", "Hurtado", numbers, "10001", "patito_1", "1015", false));
    
            
    

    }

    public boolean addUser(User user){
        return userList.add(user);
    }

    public LinkedList<User> searchUser(String usertOSearch){
        int strLen = usertOSearch.length();
        LinkedList<User> toReturn = new LinkedList<>();
        User user;
        String uId;
        String uName; 
        String uLastName; 
        boolean finded;

        if (strLen < 4){
            return userList;
        }

        Iterator<User> iter = userList.iterator();
       

        while(iter.hasNext()){
            finded = false;
            user = iter.next();
            uId= user.getId();
            uName = user.getNames();
            uLastName = user.getLastNames();

            if(uName.length() >= strLen){

                if(uName.substring(0, strLen).equals(usertOSearch)){
                    finded = true;
                }
            }

            if(uLastName.length() >= strLen){

                if(uLastName.substring(0, strLen).equals(usertOSearch)){
                    finded = true;
                }
            }

            if(uId.length() >= strLen){

                if(uId.substring(0, strLen).equals(usertOSearch)){
                    finded = true;
                }
            }
            
            if(finded){
                toReturn.add(user);
            }
        }

        return toReturn;
    }

    public boolean existUserByName(String userName){
        
        Iterator<User> iter = userList.iterator();

        while(iter.hasNext()){
            User user = iter.next();

            if(user.getUserName().equals(userName)){
                return true;
            }
        }

        return false;
       
    
    }
    public boolean editUser(User oldRoute, User editedRoute){

        return userList.replace(oldRoute, editedRoute, new Predicate<User>() {

            @Override
            public boolean test(User t) {
                return true;
            }
            
        });
    }
    public int getEmployeeIdCounter() {
        return employeeIdCounter;
    }

    public void setEmployeeIdCounter(int employeeIdCounter) {
        this.employeeIdCounter = employeeIdCounter;
    }

    public User[] userArray(){
        User [] userArray = new User[userList.size()];
        int cnt = 0;
        Iterator<User> iter = userList.iterator();

        while(iter.hasNext()){
            userArray[cnt] = iter.next();
            cnt++;
        }
        return userArray;
    }


    public User[] userArray( LinkedList<User> userList){
        User [] userArray = new User[userList.size()];
        int cnt = 0;
        Iterator<User> iter = userList.iterator();

        while(iter.hasNext()){
            userArray[cnt] = iter.next();
            cnt++;
        }
        return userArray;
    }



    
}
