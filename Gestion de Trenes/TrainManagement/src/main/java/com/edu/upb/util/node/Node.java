package com.edu.upb.util.node;

/**
 * This interface represents a node in a data structure. It provides methods to set and get the element in the node, compare the element with another element and clone the node.
 */
public interface Node<E> {

    /**
     * Sets an element in the node.
     * @param element The element to be set in the node.
     */
    public void set(E element);

    /**
     * Gets the element contained in the node.
     * @return Returns the element in the node or null.
     */
    public E get();

    /**
     * Gets a string representation of the node.
     * @return Returns a string representation of the node.
     */
    public String toString();
    
}
