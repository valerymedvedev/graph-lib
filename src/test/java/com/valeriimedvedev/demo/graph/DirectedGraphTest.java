package com.valeriimedvedev.demo.graph;

import com.valeriimedvedev.demo.graph.core.Graph;
import com.valeriimedvedev.demo.graph.core.GraphType;
import com.valeriimedvedev.demo.graph.core.Paths;
import com.valeriimedvedev.demo.graph.core.impl.DefaultGraph;
import com.valeriimedvedev.demo.graph.core.impl.DepthFirstPaths;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DirectedGraphTest
{
   private static final int MAX_GRAPH_CAPACITY = 25;
   final static Graph<Integer> graph = new DefaultGraph<>(GraphType.DIRECTED);

   private static int getRandomNumber(int a, int b)
   {
      if (b < a)
      {
         return getRandomNumber(b, a);
      }
      return a + (int) ((1 + b - a) * Math.random());
   }

   @BeforeClass
   public static void setUp() throws Exception
   {
      System.out.println("Initializing DIRECTED graph<int>...");
      final Random rand = new Random();

      // init vertices
      for (int i = 0; i < MAX_GRAPH_CAPACITY; i++)
      {
         graph.addVertex(i);
      }

      // init edges
      for (int i = 0; i < MAX_GRAPH_CAPACITY; i++)
      {
         for (int j = 0; j < MAX_GRAPH_CAPACITY; j++)
         {
            if (i != j && (getRandomNumber(1, 100) <= 10)) // ~ 10% probability
            {
               graph.addEdge(i, j);
            }
         }
      }
      System.out.println("Initialized:");
      System.out.println(graph);
   }

   @Test
   public void testSize()
   {
      assertEquals("Invalid initiated size", MAX_GRAPH_CAPACITY, graph.vertexCount());
   }

   @Test
   public void testEdgeMySelf()
   {
      final int randVertex = getRandomNumber(0, MAX_GRAPH_CAPACITY - 1);
      List<Pair<Integer, Integer>> paths = graph.getPath(randVertex, randVertex);
      assertTrue("randVertex + " + randVertex + " failed.", paths.stream().anyMatch(item -> item.getKey().equals(item.getValue())));
   }

   @Test
   public void printAllEdgesForRandNode()
   {
      int randInt = getRandomNumber(0, MAX_GRAPH_CAPACITY - 1);
      Paths<Integer> paths = new DepthFirstPaths<>(graph, randInt);
      for (int i = 0; i < MAX_GRAPH_CAPACITY; i++)
      {
         System.out.print("Rand<int> " + randInt + " has path to " + i + ": " + paths.hasPathTo(i) + "::");
         if (paths.hasPathTo(i))
         {
            for (Integer j : paths.pathTo(i))
            {
               System.out.print(j + " ");
            }
         }
         System.out.println();
      }
      assertTrue(true);

      System.out.println();
      for (int i = 0; i < MAX_GRAPH_CAPACITY; i++)
      {
         System.out.println("Path: " + randInt + " <-> " + i + ": " + graph.getPath(randInt, i));
      }
      assertTrue(true);
   }
}
