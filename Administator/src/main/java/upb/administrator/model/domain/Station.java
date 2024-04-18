package upb.administrator.model.domain;

import java.util.Objects;

public class Station {
    private String id;
    private String name;

    public Station(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Station other = (Station) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name);
    }

    
}
