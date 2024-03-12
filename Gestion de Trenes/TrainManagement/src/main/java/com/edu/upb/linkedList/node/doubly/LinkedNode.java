package com.edu.upb.linkedList.node.doubly;

import com.edu.upb.util.node.AbstractNode;

public class LinkedNode<E> extends AbstractNode<E>{
    private LinkedNode<E> next;
    private LinkedNode<E> prev;

    public LinkedNode(){
        super();
        next = null;
        prev = null;
    }

    public LinkedNode( E element){
        super(element);
        next = null;
        prev = null;
    }

    public void setNext(LinkedNode<E> next){
        this.next = next;

    }

    public LinkedNode<E> getNext(){
        return next;
    }
    public void setPrev(LinkedNode<E> prev){
        this.prev = prev;

    }

    public LinkedNode<E> getPrev(){
        return prev;
    }

}
