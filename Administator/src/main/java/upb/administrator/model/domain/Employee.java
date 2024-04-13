package upb.administrator.model.domain;

import com.edu.upb.array.Array;

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

    
}
