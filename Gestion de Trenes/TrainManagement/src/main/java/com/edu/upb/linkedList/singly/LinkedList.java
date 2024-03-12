package com.edu.upb.linkedList.singly;

import java.util.NoSuchElementException;
import java.util.function.ToIntFunction;

import com.edu.upb.linkedList.node.singly.LinkedNode;
import com.edu.upb.util.iterator.Iterator;
import com.edu.upb.util.list.AbstractList;
import com.edu.upb.util.list.List;

public class LinkedList<E> extends AbstractList<E>{
    
    protected LinkedNode<E> head;
    protected LinkedNode<E> tail;
    

    public LinkedList(){
        super();
        head = null;
        tail = null;
    }
    public LinkedList(E element){
        super();
        add(element);
    }



    @Override
    public boolean clear() {
        try{
            head = null;
            tail = null;
            amtData = 0;
            return true;
        }catch(Exception e){
            return false;
        }
    }



    @Override
    public boolean reverse() {

        try{
            LinkedList<E> reverseList  = new LinkedList<>();
            
            Iterator<E> iter1 = iterator();

            while(iter1.hasNext()){
                reverseList.addFirst(iter1.next());
            }

            Iterator<E> iter2 = reverseList.iterator();
            clear();

            while(iter2.hasNext()){
                add(iter2.next());
            }
            return true;

        }catch(Exception e){
            return false;
        }
       
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            LinkedNode<E> inode = head; 

            @Override
            public boolean hasNext() {

                return inode != null;
            }

            @Override
            public E next() {

                if(hasNext() == false){
                    throw new NoSuchElementException("No more elements in the list."); //check
                }
                E ret = inode.get();
                inode = inode.getNext();
                return ret;
            }
            

        };
    }

    @Override
    public boolean add(E element) {
        try{
            LinkedNode<E> node = new LinkedNode<>();
            node.set(element);

            if(this.isEmpty()){
                tail = node;
                head = node;
                
            }else{
                tail.setNext(node);
                tail = node;
            }
            amtData++;
            return true;

        }catch(Exception e){
            return false;
        }
    }


    @Override
    public boolean addFirst(E element) {
        try{
            LinkedNode<E> node = new LinkedNode<>();
            node.set(element);

            if(isEmpty()){

                tail = node;
                head = node;
                this.amtData++;

            }else{

                node.setNext(head);
                head = node;
                this.amtData++;
            }
            return true;

        }catch(Exception e){
            return false;
        }
    }
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
    public E pollLast() {
        if (isEmpty()){
            return null;
        }else{
            E ret = tail.get();

            LinkedNode<E> node = head;

            while(!node.getNext().equals(tail) ){
                node = node.getNext();
            }

            tail = node;
            tail.setNext(null);
            amtData--;
            return ret;

        }

    }

    @Override
    public E[] pollLastArray(int n) {
        if( n <= 0){
            @SuppressWarnings("unchecked")
            E[] arrayRet  = (E[]) new Object[1];
            return arrayRet;
        }

        @SuppressWarnings("unchecked")
        E[] array  = (E[]) new Object[n];

        for(int ii = n -1; ii >= 0; ii--){
            array[ii] = pollLast();
        }
        return array;

    }

    @Override
    public List<E> pollCollection(int n) {
        LinkedList<E> list = new LinkedList<>();
        
        int top;

        if(amtData < n){
            top = amtData;
        }else{
            top = n;
        }


        for(int ii = 0; ii < top; ii++){
            list.add(this.poll());
        }
        return list;
    }

    @Override
    public List<E> pollLastCollection(int n) {
        LinkedList<E> list = new LinkedList<>();

        int top;

        if(amtData < n){
            top = amtData;
        }else{
            top = n;
        }


        for(int ii = 0; ii < top; ii++){
            list.addFirst(this.pollLast());
        }
        return list;
    }


    @Override
    public boolean remove(E element) {

        try{
            if(isEmpty()){
                return false;
            }
            
            LinkedNode<E> inode = new LinkedNode<>();
            inode = head;
           

            if(inode.get().equals(element) ){
                head = head.getNext();
                if(head == null){
                    tail = null;
                }

                amtData--;
                return true;
            }
            
            while (inode.getNext() != null) {
                if(inode.getNext().get().equals(element) ){

                    if(inode.getNext().getNext() != null){
                        inode.setNext(inode.getNext().getNext());
                        amtData--;
                        return true;
                    }else{
                        inode.setNext(null);
                        tail = inode;
                        amtData--;
                        return true;
                    }
                   

                }
                
                inode = inode.getNext();
                
             }
            return false;

        }catch(Exception e){
            return false;
        }

        
    }
  
        

    @Override
    public boolean set(E index, E element) {
        try{
                
            LinkedNode<E> inode = head;

            while(inode != null){

                if(inode.get().equals(index) ){
                    inode.set(element);
                    return true;
                }
                inode = inode.getNext();
            }
            return false;
            
            }catch(Exception e){
                return false;
            }
    }
    
  
    @Override
    public boolean sort(ToIntFunction<E> toInt) { // All elements are remplaced with ToIntFunction and then sorted? Or arent remplaced?
        try{
            LinkedList<E> listCopy = new LinkedList<>();
            LinkedNode<E> inode;
            E minor;

            if(isEmpty()){
                return false;
            }

            while(!isEmpty()){
                inode = head;
                minor = head.get();
                
                while(inode != null){

                    if(toInt.applyAsInt(inode.get()) <= toInt.applyAsInt(minor)){
                        minor = inode.get();
                    }
                    inode = inode.getNext();


                }
                listCopy.add(minor);
                remove(minor);

            }
            this.head = listCopy.head;
            this.tail = listCopy.tail;
            this.amtData = listCopy.amtData;

            return true;
        }catch(Exception e){
            return false;
        }
        
    }
 
    @Override
    public List<E> subList(E from, E to) {

        LinkedList<E> retList = new LinkedList<>();
        LinkedNode<E> inode = head;
        boolean copy = false;

        while(inode != null){

            if(inode.get().equals(from) ){
                copy = true;
                
            }
            if(inode.get().equals(to)){
                if(from.equals(to) ){
                    retList.add(inode.get());
                }
                return retList;
            }
            if(copy){
                retList.add(inode.get());
            }

            inode = inode.getNext();

        }
        return retList;



    }

    @Override
    public boolean isEmpty() {
        
        try{
            if(head == null && tail == null && amtData == 0){
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }

    }
    @Override
    public E peekLast() {
        return tail.get();
    }
    @Override
    public List<E> peekCollection(int n) {
        LinkedList<E> retList = new LinkedList<>();
        Iterator<E> iter = iterator();

        for(int ii = 0; ii < n; ii++){
            if(iter.hasNext()){
                retList.add(iter.next());
            } 
        }
        return retList;
    }
    @Override
    public List<E> peekLastCollection(int n) {
        LinkedList<E> retList = new LinkedList<>();
        Iterator<E> iter = iterator();

        if(n <= 0){
            return retList;
        }

        int toPass = this.amtData - n;

        for(int ii = 0; ii < toPass; ii++){
            if(iter.hasNext()){
                iter.next();
            }
        }

        while(iter.hasNext()){

            retList.add(iter.next());
        }
        return retList;

    }
   
    public String toString(){

        String reString = "[";
        LinkedNode<E> inode = head;

        while(inode != null){
            if(inode.equals(tail)){
                reString = reString  + inode.toString();
            }else{
                reString = reString  + inode.toString() + ", ";
            }
            inode = inode.getNext();
        }
        reString = reString + " ]";
        return reString;
        
    }
    @Override
    public E[] toArray() {
        if(isEmpty()){
            @SuppressWarnings("unchecked")
            E[] array  = (E[]) new Object[1];
            return array;
        }

        @SuppressWarnings("unchecked")

        E[] array  = (E[]) new Object[amtData];
        int cnt = 0;
        LinkedNode<E> inode = head;
        
       while(inode != null){
            array[cnt] = inode.get();
            cnt++;
            inode = inode.getNext();
       }
       return array;
    }
    

}
