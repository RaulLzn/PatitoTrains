package patitotrains.model.domain;

public class Luggage {
    private String idLuggage;
    private int weight;
    private Ticket ticket;

    //constructor con parametros
    public Luggage(String idLuggage, int weight, Ticket ticket) {
        this.idLuggage = idLuggage;
        this.weight = weight;
        this.ticket = ticket;
    }

    //Constructor vacio
    public Luggage() {
        this.idLuggage = "";
        this.weight = 0;
        this.ticket = Ticket.getEmptyTicket();
    }

    //Getters y Setters
    public String getIdLuggage() {
        return idLuggage;
    }

    public void setIdLuggage(String idLuggage) {
        this.idLuggage = idLuggage;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    //Devolver constructor vacio
    public static Luggage getEmptyLuggage() {
        return new Luggage();
    }
}
