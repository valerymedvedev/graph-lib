package com.valeriimedvedev.demo.graph.core;

import javafx.util.Pair;

import java.util.List;


public interface Graph<T>
{
   /**
    * Quantity of vertices in the graph.
    *
    * @return quantity
    */
   int vertexCount();

   /**
    * Quantity of edges in the graph.
    *
    * @return quantity
    */
   int edgeCount();

   /**
    * Adds a {@code vertex} to this graph.
    *
    * @param vertex
    */
   void addVertex(T vertex);

   /**
    * Adds an edge sourceVertex - targetVertex to this graph.
    *
    * @param sourceVertex one vertex in the edge
    * @param targetVertex the other vertex in the edge
    */
   void addEdge(T sourceVertex, T targetVertex) throws VertexNotFoundException;

   /**
    * Returns the vertices adjacent to vertex {@code vertex}.
    *
    * @param vertex the vertex
    * @return the vertices adjacent to vertex {@code vertex}, as an iterable
    */
   Iterable<T> adjacent(T vertex);

   List<Pair<T, T>> getPath(T sourceVertex, T targetVertex);
}
