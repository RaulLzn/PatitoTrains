package patitotrains.model.domain;


import raul.Model.array.Array;

import java.io.Serializable;

public abstract class  AbstractPerson implements Serializable {
    protected String names;
    protected String lastNames;
    protected Array<String> numbers;
    
    
    public AbstractPerson( String names, String lastNames, Array<String> numbers){
        this.names = names;
        this.lastNames = lastNames;
        this.numbers = numbers;
    }
    public AbstractPerson(){
        names = "";
        lastNames = "";
        numbers = new Array<>(5);
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public Array<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(Array<String> numbers) {
        this.numbers = numbers;
    }
}
