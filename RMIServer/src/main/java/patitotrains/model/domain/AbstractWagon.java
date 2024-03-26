package patitotrains.model.domain;

/**
 * Clase abstracta que representa un vagon
 */
public class AbstractWagon {
    protected String idWagon;

    /**
     * Constructor de la clase
     * @param idWagon identificación del vagon
     */
    public AbstractWagon(String idWagon) {
        this.idWagon = idWagon;
    }

    /**
     * Constructor vacio
     */
    public AbstractWagon() {
        this.idWagon = "";
    }

    public String getIdWagon() {
        return idWagon;
    }

    public void setIdWagon(String idWagon) {
        this.idWagon = idWagon;
    }

    /**
     * Método que retorna un vagon vacío
     * @return vagon vacío
     */
    public static AbstractWagon getEmptyAbstractWagon() {
        return new AbstractWagon();
    }
}
