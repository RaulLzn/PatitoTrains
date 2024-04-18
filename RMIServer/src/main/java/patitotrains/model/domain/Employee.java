package patitotrains.model.domain;


import raul.Model.array.Array;

public class Employee extends AbstractPerson{
    
    private String id;

    public Employee(String names, String lastNames, Array<String> numbers, String id) {
        super(names, lastNames, numbers);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", lastNames='" + lastNames + '\'' +
                ", names='" + names + '\'' +
                ", numbers=" + numbers +
                '}';
    }
}
