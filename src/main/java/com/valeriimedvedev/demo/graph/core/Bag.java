package com.valeriimedvedev.demo.graph.core;

public interface Bag<T> extends Iterable<T>
{
   /**
    * Adds the item to this bag.
    *
    * @param item the item to add to this bag
    */
   void add(T item);

   /**
    * Returns true if this bag is empty.
    *
    * @return {@code true} if this bag is empty;
    * {@code false} otherwise
    */
   boolean isEmpty();

   /**
    * Returns the number of items in this bag.
    *
    * @return the number of items in this bag
    */
   int size();
}
