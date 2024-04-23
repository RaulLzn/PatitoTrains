package patitotrains.model.domain.types;

import java.io.Serializable;

public class IdType extends AbstractType implements Serializable {

    public IdType(String id, String description) {
        super(id, description);
    }

    @Override
    public String toString() {
        return "IdType{" +
                "id='" + getId() + '\'' +
                ", description='" + getDescription() + '\'' +
                '}';
    }

}
