package com.edu.upb.util.iterator;


/**
 * The IteratorInterface interface represents an iterator over a collection of elements. It provides methods to check if the iterator has more elements and retrieve the next element.
 */
public interface Iterator <E> {
    /**
     * Determines if the iterator has more elements.
     * 
     * @return Return 'true' if the iterator has more elements, otherwise 'false'.
     */
    public boolean hasNext();

    /**
     * Gets the next element in the iteration.
     * 
     * @return Return the next element in the interation
     */
    public E next();

}
