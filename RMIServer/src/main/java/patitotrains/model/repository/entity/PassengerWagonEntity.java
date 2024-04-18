package patitotrains.model.repository.entity;

public class PassengerWagonEntity {
    private String id;
    private boolean firstWagon;

    public PassengerWagonEntity(String id, boolean firstWagon) {
        this.id = id;
        this.firstWagon = firstWagon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFirstWagon() {
        return firstWagon;
    }

    public void setFirstWagon(boolean firstWagon) {
        this.firstWagon = firstWagon;
    }
}
