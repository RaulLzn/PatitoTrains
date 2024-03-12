package com.edu.upb.util.iterable;
import com.edu.upb.util.iterator.*;
import java.util.function.Function;


/**
 * This interface represents an iterable object.
 */
public interface Iterable <E>{
    /**
     * For each element in the iterator, executes the specified action.
     * @return void
     * @param action The action to be executed on each element
     */

    public void forEach(Function<E, Void> action);//class Void represents no value, diferent to primitive void

    /**
     * Gets an iterator over the elements in the iterator.
     * @return Return an iterator over the elements in the iterator.
     */
    public Iterator<E> iterator();
    
}
