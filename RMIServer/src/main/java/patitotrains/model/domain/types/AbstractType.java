package patitotrains.model.domain.types;

public abstract class AbstractType {
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
