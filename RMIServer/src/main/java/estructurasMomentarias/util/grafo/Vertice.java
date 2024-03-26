package estructurasMomentarias.util.grafo;

import raul.Model.linkedlist.singly.LinkedList;

//Creaacion de la clase vertice, la cual se encargara de crear y representar los vertices de cualquier tipo de grafo usando listas de adyacencia
//Tambien hay que saber cuales son las aristas que salen de elste vertice y cuales son las aristas que llegan a este vertice, pueden haber varias aristas que lleguen a un vertice
//usamos LinkedList para representar las aristas que salen de este vertice
public class Vertice<E> {
    private E dato;
    private LinkedList<Arista<E>> aristas;
    private LinkedList<Arista<E>> aristasEntrantes;

    //Constructor con parametros
    public Vertice(E dato) {
        this.dato = dato;
        this.aristas = new LinkedList<Arista<E>>();
        this.aristasEntrantes = new LinkedList<Arista<E>>();
    }

    //Constructor vacio
    public Vertice() {
        this.dato = null;
        this.aristas = new LinkedList<Arista<E>>();
        this.aristasEntrantes = new LinkedList<Arista<E>>();
    }

    //Getters y Setters
    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public LinkedList<Arista<E>> getAristas() {
        return aristas;
    }

    public void setAristas(LinkedList<Arista<E>> aristas) {
        this.aristas = aristas;
    }

    public LinkedList<Arista<E>> getAristasEntrantes() {
        return aristasEntrantes;
    }

    public void setAristasEntrantes(LinkedList<Arista<E>> aristasEntrantes) {
        this.aristasEntrantes = aristasEntrantes;
    }

    //Devolver constructor vacio
    public static Vertice getEmptyVertice() {
        return new Vertice();
    }

}
