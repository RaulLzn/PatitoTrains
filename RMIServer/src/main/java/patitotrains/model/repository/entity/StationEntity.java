package patitotrains.model.repository.entity;

public class StationEntity {
    private String id;
    private String name;

    // Constructor con todos los atributos
    public StationEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters y setters
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
}
