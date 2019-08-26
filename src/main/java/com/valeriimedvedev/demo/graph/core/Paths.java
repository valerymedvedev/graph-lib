package com.valeriimedvedev.demo.graph.core;

public interface Paths<T>
{
   /**
    * Is there a path between the source vertex {@code sourceVertex} and vertex {@code vertex}?
    *
    * @param vertex the vertex
    * @return {@code true} if there is a path, {@code false} otherwise
    */
   boolean hasPathTo(T vertex);

   /**
    * Returns a path between the source vertex {@code sourceVertex} and vertex {@code vertex}, or {@code null} if no such path.
    *
    * @param vertex the vertex
    * @return the sequence of vertices on a path between the source vertex {@code sourceVertex} and vertex {@code vertex}, as an Iterable
    */
   Iterable<T> pathTo(T vertex);
}
