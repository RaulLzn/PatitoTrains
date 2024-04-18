package patitotrains.model.repository.entity;

import patitotrains.model.domain.ContactPerson;
import raul.Model.array.Array;

public class ContactPersonEntity {
    private String id;
    private String names;
    private String lastNames;
    private Array<String> numbers;

    public ContactPersonEntity(String id, String names, String lastNames, Array<String> numbers) {
        this.id = id;
        this.names = names;
        this.lastNames = lastNames;
        this.numbers = numbers;
    }

    public ContactPersonEntity(ContactPerson contactPerson) {
        this.id = contactPerson.getId();
        this.names = contactPerson.getNames();
        this.lastNames = contactPerson.getLastNames();
        this.numbers = contactPerson.getNumbers();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public Array<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(Array<String> numbers) {
        this.numbers = numbers;
    }
}
