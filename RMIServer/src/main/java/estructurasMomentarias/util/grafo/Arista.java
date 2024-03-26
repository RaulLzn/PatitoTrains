package estructurasMomentarias.util.grafo;

//Creaacion de la clase arista generica, la cual se encargara de crear las aristas del grafo
public class Arista<E> {
    private E dato;
    private int peso;

    //Constructor con parametros
    public Arista(E dato, int peso) {
        this.dato = dato;
        this.peso = peso;
    }

    //Constructor vacio
    public Arista() {
        this.dato = null;
        this.peso = 0;
    }

    //Getters y Setters
    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    //Devolver constructor vacio
    public static Arista getEmptyArista() {
        return new Arista();
    }

}
