package patitotrains.model.domain.types;

import java.io.Serializable;

public abstract class AbstractType implements Serializable {
    protected String id;
    protected String description;

    public AbstractType(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AbstractType{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
