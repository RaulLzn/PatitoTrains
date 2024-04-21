package patitotrains.model.repository.entity;

import patitotrains.model.domain.ContactPerson;
import raul.Model.array.Array;

public class ContactPersonEntity extends AbstractPersonEntity {
    private String id;

    public ContactPersonEntity(String id, String names, String lastNames, Array<String> numbers) {
        super(names, lastNames, numbers);
    }

    public ContactPersonEntity(ContactPerson contactPerson) {
        super(contactPerson.getNames(), contactPerson.getLastNames(), contactPerson.getNumbers());
        this.id = contactPerson.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ContactPersonEntity{" +
                "id='" + id + '\'' +
                ", lastNames='" + getLastNames() + '\'' +
                ", names='" + getNames() + '\'' +
                ", numbers=" + getNumbers() +
                '}';
    }
}
