package com.valeriimedvedev.demo.graph.core.impl;

import com.valeriimedvedev.demo.graph.core.Graph;
import com.valeriimedvedev.demo.graph.core.Paths;

import java.util.*;


public class DepthFirstPaths<T> implements Paths<T>
{
   private T sourceVertex;
   private Map<T, Boolean> marked = new HashMap<>();
   private Map<T, T> edgeTo = new HashMap<>();

   public DepthFirstPaths(Graph<T> graph, T sourceVertex)
   {
      this.sourceVertex = sourceVertex;
      dfs(graph, sourceVertex);
   }

   @Override
   public boolean hasPathTo(T targetVertex)
   {
      return Boolean.TRUE.equals(marked.get(targetVertex));
   }

   @Override
   public Iterable<T> pathTo(T targetVertex)
   {
      if (!hasPathTo(targetVertex))
      {
         return null;
      }

      Stack<T> path = new Stack<>();
      for (T x = targetVertex; !sourceVertex.equals(x); x = edgeTo.get(x))
      {
         path.push(x);
      }
      path.push(sourceVertex);
      return path;
   }

   // depth first search from targetVertex
   private void dfs(Graph<T> graph, T targetVertex)
   {
      marked.put(targetVertex, Boolean.TRUE);
      for (T nearestVertex : graph.adjacent(targetVertex))
      {
         if (!Boolean.TRUE.equals(marked.get(nearestVertex)))
         {
            edgeTo.put(nearestVertex, targetVertex);
            dfs(graph, nearestVertex);
         }
      }
   }
}
