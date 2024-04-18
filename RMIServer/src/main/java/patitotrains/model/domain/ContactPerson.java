package patitotrains.model.domain;


import raul.Model.array.Array;

public class ContactPerson extends AbstractPerson{
    
    private String id;

    public ContactPerson(String id, String names, String lastNames, Array<String> numbers) {
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
        return "ContactPerson{" +
                "id='" + id + '\'' +
                ", lastNames='" + lastNames + '\'' +
                ", names='" + names + '\'' +
                ", numbers=" + numbers +
                '}';
    }

}
