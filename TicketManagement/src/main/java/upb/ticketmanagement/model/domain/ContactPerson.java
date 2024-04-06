package upb.ticketmanagement.model.domain;

import com.edu.upb.array.Array;

public class ContactPerson extends AbstractPerson{
    
    private String id;

    public ContactPerson( String names, String lastNames, Array<String> numbers, String id) {
        super( names, lastNames, numbers);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
