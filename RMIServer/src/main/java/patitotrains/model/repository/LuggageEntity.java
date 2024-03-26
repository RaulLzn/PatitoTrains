package patitotrains.model.repository;

/**
 * Clase que representa la entidad de equipaje
 */
public class LuggageEntity {
    String idPassenger;
    String idVagon;

    /**
     * Constructor de la clase
     */
    public LuggageEntity(String idPassenger, String idVagon) {
        this.idPassenger = idPassenger;
        this.idVagon = idVagon;
    }
}
