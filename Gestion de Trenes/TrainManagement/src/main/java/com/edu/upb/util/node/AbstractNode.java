package com.edu.upb.util.node;

public abstract class AbstractNode<E> implements Node<E>, Cloneable {
    
    protected E element;

    protected AbstractNode(){
        this.element = null;
    }
    protected AbstractNode(E element){
        this.element = element;
    }
    public void set(E element){
        this.element = element;
    }
    public E get(){
        return element;
    }
    public String toString(){
        if(element == null){
            return null;
        }

        return element.toString();
    }
}
