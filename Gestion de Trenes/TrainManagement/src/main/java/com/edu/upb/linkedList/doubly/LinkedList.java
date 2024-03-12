package com.edu.upb.linkedList.doubly;

import java.util.NoSuchElementException;
import java.util.function.ToIntFunction;

import com.edu.upb.linkedList.node.doubly.LinkedNode;
import com.edu.upb.util.iterator.Iterator;
import com.edu.upb.util.list.AbstractList;
import com.edu.upb.util.list.List;

public class LinkedList<E> extends AbstractList<E>{

    LinkedNode<E> head;
    LinkedNode<E> tail;

    public LinkedList(){
        head = null;
        tail = null;
        amtData = 0;
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

            if(isEmpty()){
                tail = node;
                head = node;
                
            }else{
                tail.setNext(node);
                node.setPrev(tail);
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
                
            }else{
                head.setPrev(node);
                node.setNext(head);
                head = node;
            }
            amtData++;
            return true;

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
        int top;
        LinkedList<E> retList = new LinkedList<>();
        LinkedNode<E> inode;

        if(n <= 0){
            return retList;
        }

        if(n >= amtData){
            top = amtData;
        }else{
            top = n;
        }
        inode = tail;

        for(int ii = 0; ii < top ; ii++ ){
            if(inode != null){
                retList.addFirst(inode.get());
                inode = inode.getPrev();
            } 
           
        }
        return retList;
    }

    @Override
    public E pollLast() {
        E element;
        if(isEmpty()){
            return null;
        }
        element = tail.get();
        if(head.equals(tail)){
            clear();
            return element;
        }
        
        tail = tail.getPrev();
        tail.setNext(null);
        amtData--;
        return element;
        
    }

    @Override
    public E[] pollLastArray(int n) {
        int top;
        if( n <= 0){
            @SuppressWarnings("unchecked")
            E[] arrayRet  = (E[]) new Object[1];
            return arrayRet;
        }

        @SuppressWarnings("unchecked")
        E[] arrayRet  = (E[]) new Object[n];

        if(n >= amtData){
            top = amtData;
        }else{
            top = n;
        }

        for(int ii = top -1 ; ii >= 0; ii--){
            arrayRet[ii] = pollLast();
        }
        return arrayRet;
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
            LinkedNode<E> inode;

            if(isEmpty()){
                return false;
            }

            if(head.equals(tail)){
                clear();
            }

            if(head.get().equals(element)){
                head = head.getNext();
                head.setPrev(null);
                amtData--;
                return true;
            }
            inode = head.getNext();

            while(inode != tail){
                
                if(inode.get().equals(element)){
                    inode.getPrev().setNext(inode.getNext());
                    inode.getNext().setPrev(inode.getPrev());
                    amtData--;
                    return true;
                }
                inode = inode.getNext();
            }
            if(tail.get().equals(element)){
                tail = tail.getPrev();
                tail.setNext(null);
                amtData--;
                return true;
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
    public boolean sort(ToIntFunction<E> toInt) {
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
}
