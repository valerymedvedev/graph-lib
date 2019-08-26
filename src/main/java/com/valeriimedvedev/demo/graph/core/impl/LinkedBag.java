package com.valeriimedvedev.demo.graph.core.impl;

import com.valeriimedvedev.demo.graph.core.Bag;

import java.util.Iterator;


public class LinkedBag<T> implements Bag<T>
{
   private Node first;  // first node in container
   private int volume;

   /**
    * Adds the item to this bag.
    *
    * @param item the item to add to this bag
    */
   @Override
   public void add(T item)
   {
      Node oldFirst = first;
      first = new Node();
      first.item = item;
      first.next = oldFirst;
      volume++;
   }

   /**
    * Returns true if this bag is empty.
    *
    * @return {@code true} if this bag is empty;
    * {@code false} otherwise
    */
   @Override
   public boolean isEmpty()
   {
      return first == null;
   }

   /**
    * Returns the number of items in this bag.
    *
    * @return the number of items in this bag
    */
   @Override
   public int size()
   {
      return volume;
   }

   @Override
   public Iterator<T> iterator()
   {
      return new ListIterator<T>();
   }

   /**
    * Encapsulates node identity with user-defined data type.
    *
    * @param <T>
    */
   private static class Node<T>
   {
      T item;
      LinkedBag.Node<T> next;
   }

   /**
    * LinkedBag iterator.
    *
    * @param <T> user-defined data type
    */
   private class ListIterator<T> implements Iterator<T>
   {
      private LinkedBag.Node<T> current = first;

      @Override
      public boolean hasNext()
      {
         return current != null;
      }

      @Override
      public T next()
      {
         T item = current.item;
         current = current.next;
         return item;
      }

      @Override
      public void remove()
      {
         // no needs to implement
         throw new UnsupportedOperationException("Not implemented yet.");
      }
   }
}
