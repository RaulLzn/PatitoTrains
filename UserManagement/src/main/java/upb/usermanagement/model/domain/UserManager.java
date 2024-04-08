package upb.usermanagement.model.domain;

import com.edu.upb.array.Array;
import com.edu.upb.linkedList.doubly.LinkedList;
import com.edu.upb.util.iterator.Iterator;
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
    
            
    
            userList.add(new User("ana_mia", "ana123", false, 
            new Employee("Ana", "Garcia", numbers, "10001")));
    
            userList.add(new User("luis22", "luispass", true, 
            new Employee("Luis", "Hernandez", numbers, "10002")));
    
            userList.add(new User("juan202", "ki00_", false, 
            new Employee("Juan Sebastian", "Granados Jaimes", numbers, "10003")));
    
            userList.add(new User("maria123", "p4ssw0rd", false, 
            new Employee("Maria", "Gonzalez", numbers, "10004")));
    
            userList.add(new User("pedro007", "securePW", true, 
                    new Employee("Pedro", "Martinez", numbers, "10005")));
    
            userList.add(new User("laura_88", "mySecret123", false, 
                    new Employee("Laura", "Lopez", numbers, "10006")));
    
            userList.add(new User("carlosxv", "qwerty123", false, 
                    new Employee("Carlos", "Rodriguez", numbers, "10007")));
    
            userList.add(new User("ana_banana", "banana123", true, 
            new Employee("Ana", "Sanchez", numbers, "10008")));

            userList.add(new User("david93", "dav1dpass", false, 
            new Employee("David", "Garcia", numbers, "10009")));

            userList.add(new User("julia_mia", "jul1@pass", true, 
            new Employee("Julia", "Hernandez", numbers, "10010")));

            userList.add(new User("roberto22", "roberto123", false, 
            new Employee("Roberto", "Alvarez", numbers, "10011")));

            userList.add(new User("patricia_cool", "coolPass123", false, 
            new Employee("Patricia", "Perez", numbers, "10012")));

            userList.add(new User("sergio_94", "sergiopw", true, 
            new Employee("Sergio", "Romero", numbers, "10013")));
            userList.add(new User("manuel_04", "manuelPW", true, 
            new Employee("Manuel", "Hernandez", numbers, "10014")));

            userList.add(new User("natalia_22", "nataliaPW", false, 
            new Employee("Natalia", "Martinez", numbers, "10015")));

            userList.add(new User("raul99", "raulPW", false, 
            new Employee("Raul", "Lopez", numbers, "10016")));

            userList.add(new User("luciano_007", "lucianoPW", true, 
            new Employee("Luciano", "Sanchez", numbers, "10017")));

            userList.add(new User("patricia_10", "patriciaPW", false, 
            new Employee("Patricia", "Fernandez", numbers, "10018")));

            userList.add(new User("diego_mia", "diego123", false, 
            new Employee("Diego", "Perez", numbers, "10019")));

            userList.add(new User("marta09", "martaPW", true, 
            new Employee("Marta", "Gomez", numbers, "10020")));

            userList.add(new User("carlos_88", "carlosPW", false, 
            new Employee("Carlos", "Rodriguez", numbers, "10021")));

            userList.add(new User("laura99", "lauraPW", false, 
            new Employee("Laura", "Romero", numbers, "10022")));
    



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
            Employee employee =(Employee) user.getPerson();
            uId= employee.getId();
            uName = user.getPerson().getNames();
            uLastName = user.getPerson().getLastNames();

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
    public boolean editUser(User oldUser, User editedUSer){

        return userList.replace(oldUser, editedUSer, new Predicate<User>() {

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
