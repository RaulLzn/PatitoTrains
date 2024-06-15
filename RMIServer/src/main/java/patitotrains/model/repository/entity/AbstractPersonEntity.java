package patitotrains.model.repository.entity;

import patitotrains.model.domain.AbstractPerson;
import raul.Model.array.Array;

import java.io.Serializable;

public class AbstractPersonEntity implements Serializable {
    private String names;
    private String lastNames;
    private Array<String> numbers;

    public AbstractPersonEntity(String names, String lastNames, Array<String> numbers) {
        this.names = names;
        this.lastNames = lastNames;
        this.numbers = numbers;
    }

    public AbstractPersonEntity(AbstractPerson abstractPerson) {
        this.names = abstractPerson.getNames();
        this.lastNames = abstractPerson.getLastNames();
        this.numbers = abstractPerson.getNumbers();
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
