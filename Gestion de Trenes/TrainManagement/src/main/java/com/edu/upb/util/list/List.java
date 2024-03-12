package com.edu.upb.util.list;

import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import com.edu.upb.util.collection.Collection;

public interface List<E> {

    /**
     * Appends the specified element to the end of this list.
     * @param element The element to be appended to this list.
     * @return Returns 'true' if the element was added successfully, otherwise 'false'.
     */
    public boolean add(E element);

    /**
     * Appends all of the elements specified in the array to the end of this list.
     * @param array The array whose elements are to be added to the list.
     * @return Returns 'true' if the array was added successfully, otherwise 'false'.
     */
    public boolean add(E[] array);

    /**
     * Appends all of the elements in the specified collection to the end of this list.
     * @param collection The collection whose elements are to be added to the list.
     * @return Returns 'true' if the element was added successfully, otherwise 'false'.
     */

    public boolean add(Collection<E> collection);

    /**
     * Appends the specified element at the beginning of this list.
     * @param element The element to be appended to this list.
     * @return Returns 'true' if the element was added successfully, otherwise 'false'.
     */
    public boolean addFirst(E element);

    /**
     * Appends all of the elements in the specified array at the beginning of this list, in the order that they are returned by the specified collection's iterator.
     * @param array The element to be appended to this list.
     * @return Returns 'true' if the collection was added successfully, otherwise 'false'.
     */
    public boolean addFirst(E[] array);
    
    /**
     * Appends all of the elements in the specified collection at the beginning of this list, in the order that they are returned by the specified collection's iterator.
     * @param collection The element to be appended to this list.
     * @return Returns 'true' if the collection was added successfully, otherwise 'false'.
     */
    public boolean addFirst( Collection<E> collection);

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     * @returnReturns the element in the head of this list, or 'null' if this list is empty.
     */
    public E peek();

    /**
     * Retrieves, but does not remove, the last element of this list.
     * @return Returns the element in the last of this list, or 'null' if this list is empty.
     */
    public E peekLast();

    /**
     * Retrieves, but does not remove, the first n elements of the list.
     * @param n The number of elements to get.
     * @returnReturns an array containing the first n elements of the list.
     */
    public E[] peekArray(int n); 

    /**
     * Retrieves, but does not remove, the last n elements of the list.
     * @param n The number of elements to get.
     * @return Returns an array containing the last n elements of the list.
     */
    public E[] peekLastArray(int n); 

    /**
     * Retrieves, but does not remove, the first n elements of the list.
     * @param n The number of elements to get.
     * @return Returns a collection containing the first n elements of the list.
     */
    public List<E> peekCollection(int n);
    /**
     * Retrieves and removes the last n elements of the list.
     * @param n The number of elements to get.
     * @return Returns a collection containing the last n elements of the list.
     */
    public List<E> peekLastCollection(int n);

    /**
     * Retrieves and removes the head (first element) of this list.
     * @return Return the element in the head of this list, or 'null' if this list is empty.
     */
    public E poll();

    /**
     * Retrieves and removes the last element of this list.
     * @return Returns the element in the last of this list, or 'null' if this list is empty.
     */
    public E pollLast();

    /**
     * Retrieves and removes the first n elements of the list.
     * @param n The number of elements to get.
     * @return Returns an array containing the first n elements of the list.
     */
    public E[] pollArray(int n);

    /**
     * Retrieves and removes the last n elements of the list.
     * @param n The number of elements to get.
     * @return Returns an array containing the first n elements of the list.
     */

    public E[] pollLastArray(int n);

    /**
     * Retrieves and removes the first n elements of the list.
     * @param n The number of elements to get.
     * @return Returns a collection containing the first n elements of the list.
     */
    public List<E> pollCollection(int n);

    /**
     * Retrieves and removes the last n elements of the list.
     * @param n The number of elements to get.
     * @return Returns a collection containing the last n elements of the list.
     */
    public List<E> pollLastCollection(int n);

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * @param element The element to be removed from this list, if present.
     * @return Returns 'true' if the element was removed successfully, otherwise 'false'.
     */
    public boolean remove(E element);

    /**
     * Removes from this list all of its elements that are contained in the specified collection.
     * @param array The array containing elements to be removed from this list.
     * @return Returns 'true' if the collection elements was removed successfully, otherwise 'false'.
     */
    public boolean remove(E[] array);
    /**
     * Removes from this list all of its elements that are contained in the specified collection.
     * @param collection The collection containing elements to be removed from this list.
     * @return Returns 'true' if the collection elements was removed successfully, otherwise 'false'.
     */
    public boolean remove(Collection<E> collection);
    /**
     * Removes all of the elements of this list that satisfy the given predicate. Errors or runtime exceptions thrown during iteration or by the predicate are relayed to the caller.
     * @param filter A predicate which returns 'true' for elements to be removed.
     * @return Returns 'true' if the list was removed successfully, otherwise 'false'.
     */
    public boolean remove(Predicate<E> filter);

    /**
     * Replaces each element of this list with the result of applying the function to that element.
     * @param element The element to be replaced in this list.
     * @param newElement The element to be added to this list.
     * @param comparator The function to apply to each element.
     * @return Returns 'true' if the list was replaced successfully, otherwise 'false'.
     */
    public boolean replace(E element, E newElement, Predicate<E> comparator);

    /**
     * Replaces each element of this list with the result of applying the function to that element.
     * @param element The array containing elements to be replaced in this list.
     * @param newElementThe array containing elements to be added in this list.
     * @param comparator The function to apply to each element.
     * @return Returns 'true' if the list was replaced successfully, otherwise 'false'.
     */
    public boolean replace(E[] element, E[] newElement, Predicate<E> comparator);

    /**
     * Replaces each element of this list with the result of applying the function to that element.
     * @param collection The collection containing elements to be replaced in this list.
     * @param newCollection The collection containing elements to be added to this list.
     * @param comparator The function to apply to each element.
     * @return 	Returns 'true' if the list was replaced successfully, otherwise 'false'.
     */
    public boolean replace(Collection<E> collection, Collection<E> newCollection, Predicate<E> comparator);

    /**
     * Retains only the elements in this list that are contained in the specified collection.
     * @param array The array containing elements to be retained in this list.
     * @return Returns 'true' if the list was retained successfully, otherwise 'false'.
     */
    public abstract boolean retain(E[] array);

    /**
     * Retains only the elements in this list that are contained in the specified collection.
     * @param collection The collection containing elements to be retained in this list.
     * @return 	Returns 'true' if the list was retained successfully, otherwise 'false'.
     */
    public boolean retain(Collection<E> collection);
    
    /**
     * Replaces the specified element by a new element in this list. Only the first occurrence is replaced.
     * @param index The element to be replaced in this list.
     * @param element The element to be added to this list.
     * @return Returns 'true' if the element was replaced successfully, otherwise 'false'.
     */
    public boolean set(E index, E element);

    /**
     * Sorts this list according to the order induced by the specified Comparator.
     * @param toInt The Comparator used to compare list elements.
     * @return 	Returns 'true' if the list was sorted successfully, otherwise 'false'.
     */
    public boolean sort(ToIntFunction<E> toInt);

    /**
     * Returns a sub list of this list between the specified "from" and "to".
     * @param from 	The initial element of the sub list, inclusive.
     * @param to The final element of the sub list, exclusive.
     * @return Returns a sub list of this list between the specified "from", inclusive, and "to", exclusive.
     */
    public List<E> subList(E from, E to );

    /**
     * Returns an array containing all of the elements in this list.
     * @return Returns an array containing all of the elements in this list.
     */
    public E[] toArray();
}
