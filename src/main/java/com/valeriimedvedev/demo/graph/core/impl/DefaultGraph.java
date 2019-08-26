package com.valeriimedvedev.demo.graph.core.impl;

import com.valeriimedvedev.demo.graph.core.*;
import javafx.util.Pair;

import java.util.*;


public class DefaultGraph<T> implements Graph<T>
{
   private int edgeCount;
   private final GraphType type;
   private final Map<T, Bag<T>> vertexContainer;

   public DefaultGraph(GraphType type)
   {
      this(type, 16); // default map capacity
   }

   public DefaultGraph(GraphType graphType, int initialVertexCount)
   {
      if (initialVertexCount < 0)
      {
         throw new IllegalArgumentException("Number of vertices must be non-negative");
      }

      edgeCount = 0;
      type = graphType;
      vertexContainer = new HashMap<>(initialVertexCount);

      for (Map.Entry<T, Bag<T>> entry : vertexContainer.entrySet())
      {
         entry.setValue(new LinkedBag<T>());
      }
   }

   @Override
   public int vertexCount()
   {
      return vertexContainer.size();
   }

   @Override
   public int edgeCount()
   {
      return edgeCount;
   }

   @Override
   public void addVertex(T vertex)
   {
      if (!isExists(vertex))
      {
         vertexContainer.put(vertex, new LinkedBag<T>());
      }
   }

   @Override
   public void addEdge(T vertex1, T vertex2) throws VertexNotFoundException
   {
      if (!isExists(vertex1) || !isExists(vertex2))
      {
         throw new VertexNotFoundException("Vertex is not found.");
      }

      vertexContainer.get(vertex1).add(vertex2);
      if (GraphType.UNDIRECTED.equals(type))
      {
         vertexContainer.get(vertex2).add(vertex1);
      }
      edgeCount++;
   }

   @Override
   public Iterable<T> adjacent(T vertex)
   {
      return vertexContainer.get(vertex);
   }

   @Override
   public List<Pair<T, T>> getPath(T sourceVertex, T targetVertex)
   {
      final List<Pair<T, T>> pathList = new ArrayList<>();
      Paths<T> paths = new DepthFirstPaths<>(this, sourceVertex);
      if (paths.hasPathTo(targetVertex))
      {
         T left = sourceVertex;
         for (T vertex : paths.pathTo(targetVertex))
         {
            T right = vertex;
            pathList.add(new Pair<T, T>(left, right));
            left = vertex;
         }
      }
      if (pathList.size() > 1)
         pathList.remove(0); // remove redundant pair source-target
      return pathList;
   }

   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      s.append(vertexCount() + " vertices, " + edgeCount() + " edges " + System.getProperty("line.separator"));
      for (Map.Entry<T, Bag<T>> entry : vertexContainer.entrySet())
      {
         s.append(entry.getKey() + ": ");
         Iterator<T> bagIterator = entry.getValue().iterator();
         while (bagIterator.hasNext())
         {
            T item = bagIterator.next();
            s.append(item + " ");
         }
         s.append(System.getProperty("line.separator"));
      }
      return s.toString();
   }

   protected boolean isExists(T vertex)
   {
      if (vertexContainer.containsKey(vertex))
      {
         return true;
      }
      return false;
   }
}
