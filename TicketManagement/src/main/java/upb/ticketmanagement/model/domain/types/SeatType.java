package upb.ticketmanagement.model.domain.types;

public class SeatType extends AbstractType{
    
    private double costPerKm;

    public SeatType(String id, String description, double costPerKm) {
        super(id, description);
        this.costPerKm = costPerKm;
    }

    public double getCostPerKm() {
        return costPerKm;
    }

    public void setCostPerKm(double costPerKm) {
        this.costPerKm = costPerKm;
    }
}
