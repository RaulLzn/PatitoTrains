package com.edu.upb.util.collection;
import com.edu.upb.util.iterable.Iterable;

/**
 * This interface represents a collection of elements.
 */

public interface Collection<E> extends Iterable<E>{
    /**
     * Removes all elements from the collection.
     * @return Return 'true' if the list was cleared successfully, otherwise 'false'.
     */
    public boolean clear();

    /**
     * Determines if the collection contains the specified element.
     * @param element the element to search for.
     * @return Return 'true' if the collection contains the specified element, otherwise 'false'.
     */
    public boolean contains(E element);

    /**
     * Determines if the collection contains the specified elements in the array.
     * @param array the array containing elements to be searched for in this collection.
     * @return Return 'true' if the collection contains the specified elements in the array, otherwise 'false'.
     */
    public boolean contains(E[] array);

    /**
     * Determines if the collection contains the specified elements.
     * @param collection
     * @return 	
Returns 'true' if the collection contains the specified elements, otherwise 'false'.
     */
    public boolean contains(Collection<E> collection);

    /**
     * Determines if the collection is empty.
     * @return Returns 'false' if the collection contains any element, otherwise 'true'.
     */
    public boolean isEmpty();

    /**
     * Redistributes the elements in the collection in reverse order.
     * @return Returns 'true' if the collection was reversed successfully, otherwise 'false'.
     */
    public boolean reverse();

    /**
     * Gets the size of the collection.
     * @return Returns the number of elements in this collection.
     */
    public int size();


    
}
