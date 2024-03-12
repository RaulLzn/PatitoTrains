package com.edu.upb.array;

import java.util.function.Predicate;

import com.edu.upb.util.array.AbstractArray;
import com.edu.upb.util.collection.Collection;
import com.edu.upb.util.iterator.Iterator;

@SuppressWarnings("unchecked")
/**
 * This class represents a collection of elements that can be accessed by an index.
 */
public class Array<E> extends AbstractArray<E>{


    private E elements[];
    private int amtData;

    public int length(){
        return elements.length;
    }
    /**
     * This constructor initializes an instance of the Array class with the specified amount of data.
     * @param amtData The amount of data to be initialized in the Array.            
     */
    public Array(int size) {
        amtData = 0;
        elements = (E[]) new Object[size];
    }
    /**
     * Returns the index of the first occurence of null.
     * 
     * @return return the index of the first occurrence of null, if there isnt any null will return -1.
     */
    private int getAvailableIndex(){
        try{

            for(int ii = 0; ii < elements.length; ii++){
                if(elements[ii] == null){
                    return ii;
                }
            }

        }catch(Exception e){
            return -1;
        }
        return -1;
    }

    @Override // ask about override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0; 

            @Override
            public boolean hasNext() {
                if(index < elements.length){
                    return true;
                }
                else{
                    return false;
                }
                
            }

            @Override
            public E next() {
                return elements[index++];
            }
            

        };
        
    }

    @Override
    public boolean add(E element) {
        
        try{
            int index = getAvailableIndex();

            if(index == -1){
                return false;
            }

            elements[index] = element;
            this.amtData++;
            return true;
        }catch(Exception e){
            return false;
        }
        
    }
    @Override
    public int indexOf(E element){
    
        for(int ii = 0; ii < elements.length ; ii++){
            if(elements[ii].equals(element) ){
                return ii;
            }
        }
            
        return -1;
    } 
    @Override
    public int lastIndexOf(E element){
        int index = -1;
        
        for(int ii = 0; ii < elements.length ; ii++){
            if(elements[ii].equals(element)){
                index = ii;
            }
        }
            
        return index;
    } 
    @Override
    public boolean add(int index, E[] array) {
        

        try{
            boolean ret = false;
            for(E arrayElement: array){
                if(index < elements.length){
                    elements[index] = arrayElement;
                    ret = true;
                    this.amtData++;
                    index++;
                }
    
            }
            return ret;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean add(int index, Collection<E> collection) {
        

        try{
            Iterator<E> iter = collection.iterator();
            boolean ret = false;
            if(index < 0){
                return ret;
            }
    
            while(iter.hasNext()){
                if(index < elements.length){
                    elements[index] = iter.next();
                    this.amtData++;
                    index++;
                    ret = true;
                }
            }
            return ret;
        }catch(Exception e){
            return false;
        }
        
    }

    @Override
    public void defragment() {
        E[] arrayCopy = (E[]) new Object[elements.length];
        int cntCopy = 0;
      
            for(int ii = 0; ii < elements.length; ii++){
                if(elements[ii] != null){
                    arrayCopy[cntCopy] = elements[ii];
                    cntCopy++;
                }
            }
    
            for(int ii = 0; ii < elements.length; ii++){
                elements[ii] = arrayCopy[ii];
            }
        
        

    }

    @Override
    public E get(int index) {
        return elements[index];
    }

    @Override
    public boolean remove(int index) {

        try{
            if(index < elements.length && index >= 0){
                elements[index] = null;
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
        
       
    }

    @Override//ASK ABOUT THIS ONE
    public boolean remove(Predicate<E> filter) {

        try{
            boolean ret = false;

            for(int ii = 0; ii < elements.length; ii++){
    
                if(filter.test(elements[ii])){
                    elements[ii] = null;
                    ret = true;
                }
                
            }
            return ret;
        }catch(Exception e){
            return false;
        }
       
    }

    @Override
    public boolean remove(int from, int to) {
       
        try{
            int top;
            boolean ret = false;
            if(to > elements.length){
                top = elements.length;
            }
            else{
                top = to;
            }
    
            for(int ii = from; ii < top; ii++){
                elements[ii] = null;
                ret = true;
            }
    
            return ret;
        }catch(Exception e){
            return false;
        }

        
        
    }

    @Override
    public boolean dimension(int newDimension){ // Ask about the return
        
        try{
            boolean rt = false;
            E[] arrayCopy = (E[]) new Object[amtData];
            int top;
            if(newDimension == elements.length || newDimension <= 0){

                return false;
            }

            
            for(int ii = 0; ii < amtData; ii++){
                arrayCopy[ii] = elements[ii];

            }
            

            this.elements = (E[]) new Object[newDimension];

            if(arrayCopy.length > elements.length){
                top = elements.length;
                amtData = elements.length;
            }else{
                top= arrayCopy.length;
            }

            for(int ii = 0; ii < top; ii++){
                elements[ii] = arrayCopy[ii];
            }

            if(elements.length == newDimension){
                rt = true;
            }
            return rt;      
        }catch(Exception e){
            return false;
        }
        
    }
    @Override
    public boolean set(int index, E element) {
        try{
            if( index < elements.length && index >= 0){
                elements[index] = element;
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
        
    }

    @Override // ask about this one
    public boolean clear() {
        try{
            boolean rt = false;
            for(int ii = 0 ; ii < elements.length; ii++){
                elements[ii] = null;
                rt = true;
                amtData  = 0;
            }
            return rt;
        }catch(Exception e){
            return false;
        }
        
    }

    // ask about this one return
    @Override
    public boolean reverse() {

        try{
            boolean rt = false;
            E[] arrayCopy = (E[]) new Object[elements.length];
            
            for(int ii = 0; ii < elements.length; ii++){
                arrayCopy[ii] = elements[elements.length - 1 - ii];
            }

            for(int ii = 0; ii < elements.length; ii++){
                elements[ii] = arrayCopy[ii];
                rt = true;
            }

            return rt;
        }catch(Exception e){
            return false;
        }

    }
        
        
    
    @Override
    public String toString() {
 
            String r = "[";
            int noComma = elements.length - 1;
    
            for (int ii = 0; ii < elements.length; ii++){
                r += elements[ii];
    
                if(ii < noComma){
                    r+= ", ";
                }
                
            }
            r+= "]";
    
            return r;
        
        
    }
    @Override
    public int size() {
        return amtData;
    }
}
