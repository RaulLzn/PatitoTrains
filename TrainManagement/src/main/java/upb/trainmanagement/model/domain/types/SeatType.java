package upb.trainmanagement.model.domain.types;

public class SeatType extends AbstractType{
    private int costPerKm;

    public SeatType(String id, String description, int costPerKm) {
        super(id, description);
        this.costPerKm = costPerKm;
    }

    public int getCostPerKm() {
        return costPerKm;
    }

    public void setCostPerKm(int costPerKm) {
        this.costPerKm = costPerKm;
    }
    
}
