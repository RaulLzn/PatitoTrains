package upb.trainmanagement.model.domain;

import com.edu.upb.array.Array;

import upb.trainmanagement.model.domain.types.IdType;

public class Passenger extends AbstractPerson {
    private String id;
    private IdType idType;
    private String address;
    private ContactPerson contactPerson;

    public Passenger(String names, String lastNames, Array<String> numbers, String id, IdType idType, String address,
            ContactPerson contactPerson) {
        super(names, lastNames, numbers);
        this.id = id;
        this.idType = idType;
        this.address = address;
        this.contactPerson = contactPerson;
    }
    public Passenger(String id, IdType idType, String address, ContactPerson contactPerson) {
        this.id = id;
        this.idType = idType;
        this.address = address;
        this.contactPerson = contactPerson;
    }
    public IdType getIdType() {
        return idType;
    }
    public void setIdType(IdType idType) {
        this.idType = idType;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public ContactPerson getContactPerson() {
        return contactPerson;
    }
    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    



}
