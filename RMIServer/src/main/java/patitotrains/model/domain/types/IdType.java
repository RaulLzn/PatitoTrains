package patitotrains.model.domain.types;

import java.io.Serializable;

/**
 * Clase que representa un tipo de identificación.
 */
public class IdType extends AbstractType implements Serializable {

    /**
     * Constructor de la clase.
     *
     * @param id          ID del tipo.
     * @param description Descripción del tipo.
     */
    public IdType(String id, String description) {
        super(id, description);
    }

    /**
     * Método que retorna el ID del tipo.
     *
     * @return ID del tipo.
     */
    @Override
    public String toString() {
        return "IdType{" +
                "id='" + getId() + '\'' +
                ", description='" + getDescription() + '\'' +
                '}';
    }

}
