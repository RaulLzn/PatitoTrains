package com.edu.upb.util.list;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import com.edu.upb.array.Array;
import com.edu.upb.util.collection.Collection;
import com.edu.upb.util.iterator.Iterator;

public abstract class AbstractList<E> implements List<E>, Cloneable, Collection<E>{
    protected int amtData;

    protected AbstractList(){
        amtData = 0;
    }
    @Override
    public abstract boolean add(E element) ;

    @Override
    public  boolean add(E[] array){
        try{
            for(int ii = 0; ii < array.length; ii++){
                this.add(array[ii]);

            }
            return true; // Ask about true conditions


        }catch(Exception e){
            return false;
        }
    }

    @Override
    public  boolean add(Collection<E> collection){
        try{
            Iterator<E> iter = collection.iterator();

            while(iter.hasNext()){
                this.add(iter.next());

            }
            return true; // Ask about the return 

        }catch(Exception e){
            return false;
        }
    }

    @Override
    public abstract boolean addFirst(E element);

    @Override
    public  boolean addFirst(E[] array){
        try{

            for(int ii = 0; ii < array.length; ii++){
                addFirst(array[ii]);

            }
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public  boolean addFirst(Collection<E> collection){
        try{
            Iterator<E> iter = collection.iterator();

            while(iter.hasNext()){
                addFirst(iter.next());

            }
            return true; // Ask about the return 

        }catch(Exception e){
            return false;
        }
    }

    @Override
    public E peek() {
        try{    

            Iterator<E> iter = this.iterator();


            if(iter.hasNext()){
                return iter.next();
            }
            return null;

        }catch(Exception e){
            return null;
        }
    }

    @Override
    public abstract E peekLast();

    @Override
    public E[] peekArray(int n) {
        try{    
            if( n <= 0){
                @SuppressWarnings("unchecked")
                E[] arrayRet  = (E[]) new Object[1];
                return arrayRet;
            }
            @SuppressWarnings("unchecked")

            E[] array  = (E[]) new Object[amtData];
            int cnt = 0;
            Iterator<E> iter = this.iterator();
            
            if(n <= 0){
                return array;
            }


            for(int ii = 0; ii < n; ii++){
                if(iter.hasNext()){
                    array[cnt] = iter.next();
                    cnt++;
                }
            }

            return array;
            
        }catch(Exception e){
            @SuppressWarnings("unchecked")
            E[] array  = (E[]) new Object[amtData];
            return array;
        }
        
    }

    @Override
    public E[] peekLastArray(int n) {
        try{   
            if( n <= 0){
                @SuppressWarnings("unchecked")
                E[] arrayRet  = (E[]) new Object[1];
                return arrayRet;
            }

            @SuppressWarnings("unchecked")

            E[] array  = (E[]) new Object[amtData];
            int cnt = 0;
            Iterator<E> iter = this.iterator();

            int toPass = this.amtData - n;

            for(int ii = 0; ii < toPass; ii++){
                if(iter.hasNext()){
                    iter.next();
                }
            }

            
            for(int ii = 0; ii < n; ii++){
                if(iter.hasNext()){
                    array[cnt] = iter.next();
                    cnt++;
                }
            }

            return array;
            
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public abstract List<E> peekCollection(int n) ;

    @Override
    public abstract List<E> peekLastCollection(int n);
    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        E element = peek();

        remove(element);
        return element;
    }
    @Override
    public abstract E pollLast();

    @Override
    public E[] pollArray(int n) {
        
        if( n <= 0){
            @SuppressWarnings("unchecked")
            E[] arrayRet  = (E[]) new Object[1];
            return arrayRet;
        }
        @SuppressWarnings("unchecked")
        E[] arrayRet  = (E[]) new Object[n];

    


        for(int ii = 0; ii < n; ii ++){
            arrayRet[ii] = poll();
        }
        return arrayRet;
       
    }

    @Override
    public abstract E[] pollLastArray(int n);


    @Override
    public abstract List<E> pollCollection(int n);

    @Override
    public abstract List<E> pollLastCollection(int n);

    @Override
    public abstract boolean remove(E element);

    @Override
    public  boolean remove(E[] array){
        try{
            boolean ret = true;
            for (E element : array) {
                if(!remove(element)){
                    ret = false;
                }
                
            }
            return ret;

        }catch(Exception e){
            return false;
        }
        
    }

    @Override
    public boolean remove(Collection<E> collection){
        try{
            Iterator<E> iter = collection.iterator();
            boolean ret = true;

            while(iter.hasNext()){
                   if(!remove(iter.next())){
                        ret = false;
                   }
            }
            return ret;

        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean remove(Predicate<E> filter){
        try{
            Iterator<E> iter = iterator();
            boolean ret = false;
            while(iter.hasNext()){
                E element = iter.next();

                if(filter.test(element)){

                    if(remove(element)){
                        ret = true;
                    }
                    
                }
            }
            return ret;

        }catch(Exception e){
            return false;
        }
        
    }

    @Override
    public  boolean replace(E element, E newElement, Predicate<E> comparator){
        try{
            if(!contains(element)){
                return false;
            }
            if(comparator.test(element)){
                set(element, newElement);
                return true;
            }
            return false;


        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean replace(E[] element, E[] newElement, Predicate<E> comparator) {

        try{
            boolean ret = true;
            int top = element.length;

            if(newElement.length < element.length){
                top = newElement.length;
                ret = false;
            }

            for(int ii = 0; ii < top; ii++){
                
                if(!replace(element[ii], newElement[ii], comparator )){
                    ret = false;
                }
            }
            return ret;
        }catch(Exception e){
            return false;
        }
        
    }


    public void setAmtData(int amtData) {
        this.amtData = amtData;
    }

   
    @Override
    public boolean replace(Collection<E> collection, Collection<E> newCollection, Predicate<E> comparator) {
        try{
            boolean ret = true;
            int top = collection.size();
            Iterator<E> iter = collection.iterator();
            Iterator<E> newIter = newCollection.iterator();

            if(newCollection.size() < collection.size()){
                ret = false;
                top = newCollection.size();
            }

            for(int ii  = 0; ii < top; ii++){
                if(!replace(iter.next(), newIter.next(), comparator)){
                    ret = false;
                }

            }
            

            return ret;



        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean retain(E[] array) {
        
        Array<E> toRetain = new Array<>(array.length);
        boolean ret = contains(array);
        toRetain.add(0, array);

        try{
            Predicate<E> predicate = new Predicate<E>() {

                @Override
                public boolean test(E t) {
                    return !toRetain.contains(t);
                }
                
            };
           remove(predicate);
           return ret;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean retain(Collection<E> collection) {
        boolean ret = contains(collection);
        try{
            Predicate<E> predicate = new Predicate<E>() {

                @Override
                public boolean test(E t) {
                    return !collection.contains(t);
                }
                
            };
           remove(predicate);
           return ret;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public abstract boolean set(E index, E element);

    @Override
    public abstract boolean sort(ToIntFunction<E> toInt);

    @Override
    public abstract List<E> subList(E from, E to); // ASK

    @Override
    public abstract E[] toArray();
    
    public int size(){
        
        return amtData;
    }

    public void forEach(Function<E, Void> action){
        Iterator<E> iter = this.iterator();

        while(iter.hasNext()){
            action.apply(iter.next());
        }

      
    }


    public boolean contains(E element){

        try{
            Iterator<E> iter = this.iterator();

            while(iter.hasNext()){
    
                if(iter.next().equals(element)){
                    return true;
                }
    
            }
            return false;
        }catch(Exception e){
            return false;
        }
        
    }


    public boolean contains(E[] array) {

        try{
            for(E element: array ){

                if(this.contains(element) == false){
                    return false;
                }
    
            }
            return true;

        }catch(Exception e){
            return false;
        }

    }


    public boolean contains(Collection<E> collection) {

        try{
            Iterator<E> iter = collection.iterator();

            while(iter.hasNext()){
    
                if(this.contains(iter.next()) == false){
    
                    return false;
                }
            }
    
            return true;

        }catch(Exception e){
            return false;
        }
        
    }
    

    public abstract boolean isEmpty();
    
}
