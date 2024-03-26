package estructurasMomentarias.grafo;

import estructurasMomentarias.util.grafo.Arista;
import estructurasMomentarias.util.grafo.Vertice;

import raul.Model.util.Iterator.Iterator;

//Creaacion de la clase grafo generico, la cual se encargara de crear y representar los grafos, cualquier tipo de grafo
public class Grafo<E> {
    private int numVertices;
    private int numAristas;
    private Vertice<E> vertices[];

    //Constructor con parametros
    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        this.numAristas = 0;
        this.vertices = new Vertice[numVertices];
    }

    //Constructor vacio
    public Grafo() {
        this.numVertices = 0;
        this.numAristas = 0;
        this.vertices = new Vertice[0];
    }

    //Getters y Setters
    public int getNumVertices() {
        return numVertices;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public int getNumAristas() {
        return numAristas;
    }

    public void setNumAristas(int numAristas) {
        this.numAristas = numAristas;
    }

    public Vertice<E>[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertice<E>[] vertices) {
        this.vertices = vertices;
    }

    //Devolver constructor vacio
    public static Grafo getEmptyGrafo() {
        return new Grafo();
    }


    public void addVertice(Vertice<E> vertice) {
        if (numVertices < vertices.length) {
            vertices[numVertices] = vertice;
            numVertices++;
        }
    }

    //Metodo para agregar una arista al grafo
    public void addArista(int origen, int destino, int peso) {
        if (origen < numVertices && destino < numVertices) {
            vertices[origen].getAristas().add(new Arista<>(vertices[destino].getDato(), peso));
            vertices[destino].getAristasEntrantes().add(new Arista<>(vertices[origen].getDato(), peso));
            numAristas++;
        }
    }
}
