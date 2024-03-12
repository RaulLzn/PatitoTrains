package com.edu.upb.util.array;

import java.util.function.Function;
import java.util.function.Predicate;

import com.edu.upb.util.collection.Collection;
import com.edu.upb.util.iterator.Iterator;

/**
 * The Abstract Array represents a collection that supports array-like operations. It implements the CollectionInterface and provides additional methods for inserting, accessing, and anipulating elements at specific positions in the collection.
 */
public abstract class AbstractArray<E> implements Collection<E>, Array<E> {

    /**
     * amtData represents the amount of data of the array.
     */
    /**
     * This constructor initializes an instance of the AbstractArray class with the specified amount of data.
     * @param amtData The amount of data to be initialized in the AbstractArray.            
     */
    public AbstractArray(){
       
    }

    public void forEach(Function<E, Void> action){
        Iterator<E> iter = this.iterator();

        while(iter.hasNext()){
            action.apply(iter.next()); 
        }      
    }

    public abstract Iterator<E> iterator(); // in array

    public abstract boolean add(E element); // in array

    public abstract boolean add(int index, E[] array); // in array

    public abstract boolean add(int index, Collection<E> collection); // in array

    public abstract void defragment(); // in array

    public abstract E get(int index); // in array

    public abstract int indexOf(E element);

    public abstract int lastIndexOf(E element);

    public abstract boolean remove(int index); // in array

    public abstract boolean remove(Predicate<E> filter); // in array

    public abstract boolean remove(int from, int to); // in array

    public abstract boolean dimension(int newDimension); // in array

    public abstract boolean set(int index, E element); // in array

    //----------------------- COLLECTION ----------------------------

    public abstract boolean clear();

    public boolean contains(E element){
        Iterator<E> iter = this.iterator();

        while(iter.hasNext()){
            E compare = iter.next();
            if(compare != null){
                if(compare.equals( element)){
                    return true;
                }
    
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

    public boolean isEmpty(){
        Iterator<E> iter = this.iterator();

        while(iter.hasNext()){

            if(iter.next() != null){

                return false;
            }
        }

        return true;
    }


    public abstract boolean reverse();

    
}
