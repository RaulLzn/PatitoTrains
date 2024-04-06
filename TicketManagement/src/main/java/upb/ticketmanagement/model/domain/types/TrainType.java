package upb.ticketmanagement.model.domain.types;

public class TrainType extends AbstractType{
    private int cargoCapacity;

    public TrainType(String id, String description, int cargoCapacity) {
        super(id, description);
        this.cargoCapacity = cargoCapacity;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }
  
}
