package upb.trainmanagement.model.domain;

public class Lugagge {
    private String id;
    private int weight;
    private String ticketId;

    
    
    public Lugagge(String id, int weight, String ticketId) {
        this.id = id;
        this.weight = weight;
        this.ticketId = ticketId;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public String getTicketId() {
        return ticketId;
    }
    public void setTicketId(String trainId) {
        this.ticketId = trainId;
    }

    


}
