package upb.trainmanagement.model.domain;

import com.edu.upb.linkedList.doubly.LinkedList;;

public class ContainerWagon {
    
    private String id;
    private LinkedList<Lugagge> luggages;

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
    public LinkedList<Lugagge> getLuggages() {
        return luggages;
    }
    public void setLuggages(LinkedList<Lugagge> luggages) {
        this.luggages = luggages;
    }

}
