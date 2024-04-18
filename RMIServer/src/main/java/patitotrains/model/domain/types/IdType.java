package patitotrains.model.domain.types;

public class IdType extends AbstractType {

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
