package com.edu.upb.util.collection;

import java.util.function.Function;

import com.edu.upb.util.iterator.Iterator;

/**
 * The Abstract Collection represents a collection that supports collection-like operations. It implements the CollectionInterface and provides additional methods for inserting, accessing, and manipulating elements at specific positions in the collection.
 */


public abstract class AbstractCollection<E> implements Collection<E>, Cloneable{
    /**
     * amtData represents the amount of data of the collection.
     */

    public AbstractCollection(){
    }

    public abstract boolean reverse();
    
    public abstract boolean clear();

    public abstract Iterator<E> iterator();

    public abstract int size();

    public void forEach(Function<E, Void> action){
        Iterator<E> iter = this.iterator();

        while(iter.hasNext()){
            action.apply(iter.next());
        }

      
    }


    public boolean contains(E element){
        Iterator<E> iter = this.iterator();

        while(iter.hasNext()){

            if(iter.next() == element){
                return true;
            }

        }
        return false;
    }


    public boolean contains(E[] array) {
        for(E element: array ){

            if(this.contains(element) == false){
                return false;
            }

        }
        return true;

    }


    public boolean contains(Collection<E> collection) {
        Iterator<E> iter = collection.iterator();

        while(iter.hasNext()){

            if(this.contains(iter.next()) == false){

                return false;
            }
        }

        return true;
    }
    
}
