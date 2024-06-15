package patitotrains.model.domain;

import raul.Model.linkedlist.doubly.circular.LinkedList;

import java.io.Serializable;

public class ContainerWagon implements Serializable {
    
    private String id;
    private LinkedList<Luggage> luggages;

    public ContainerWagon(String id) {
        this.id = id;
        luggages = new LinkedList<>();
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinkedList<Luggage> getLuggages() {
        return luggages;
    }

    public void setLuggages(LinkedList<Luggage> luggages) {
        this.luggages = luggages;
    }

    @Override
    public String toString() {
        return "ContainerWagon{" +
                "id='" + id + '\'' +
                ", luggages=" + luggages +
                '}';
    }
}
