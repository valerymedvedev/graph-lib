package com.valeriimedvedev.demo.graph.core;

/**
 * Raised when user tries to use nonexistent vertex.
 */
public class VertexNotFoundException extends Exception
{
   public VertexNotFoundException()
   {
   }

   public VertexNotFoundException(String message)
   {
      super(message);
   }
}
