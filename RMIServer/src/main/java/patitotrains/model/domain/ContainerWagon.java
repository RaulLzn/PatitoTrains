package patitotrains.model.domain;

import raul.Model.linkedlist.doubly.circular.LinkedList;

/**
 * Clase que representa un vagón contenedor
 */
public class ContainerWagon extends AbstractWagon {
    private LinkedList<Luggage> luggages;

    /**
     * Constructor de la clase
     * @param id identificación del vagon
     * @param luggages equipajes del vagon
     */
    public ContainerWagon(String id, LinkedList<Luggage> luggages) {
        super(id);
        this.luggages = luggages;
    }

    /**
     * Constructor vacio
     */
    public ContainerWagon() {
        super();
        this.luggages = new LinkedList<>();
    }

    //Getters y Setters
    public LinkedList<Luggage> getLuggages() {
        return luggages;
    }

    public void setLuggages(LinkedList<Luggage> luggages) {
        this.luggages = luggages;
    }

    /**
     * Método que retorna un vagon contenedor vacío
     * @return vagon contenedor vacío
     */
    public static ContainerWagon getEmptyContainerWagon() {
        return new ContainerWagon();
    }

}
