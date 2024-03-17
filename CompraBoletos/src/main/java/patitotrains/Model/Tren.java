package patitotrains.Model;

import raul.Model.array.Array;

public interface  Tren {

    private int id;
    private String nombre;
    private int kilometraje;
    private String marca;
    private int capacidadCarga;
    private Array[VagonPasajero];
    private Array[VagonCarga];
    private boolean disponible;
    private boolean deBaja;

}
