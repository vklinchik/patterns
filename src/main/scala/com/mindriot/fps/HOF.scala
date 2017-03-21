package com.mindriot.fps


object HOF {

  /**
    * Convert a function of 2 arguments into partially applied function of one argument
   */
  def curry[A, B, C](f: (A, B) => C): A => (B => C) = {
    (a: A) => f(a, _)
  }

  /**
    * Convert a function of 2 arguments into partially applied function of one argument
    */
  def curry2[A, B, C](f: (A, B) => C): A => (B => C) = {
    a => b => f(a, b)
  }

  /**
    * Reverse transformation of curry
    */
  def uncurry[A, B, C](f: A => B => C): (A, B) => C = {
    (a, b) => f(a)(b)
  }

  /**
    * Compose two functions
    */
  def compose[A, B, C](f: B => C, g: A => B): A => C = {
    a => f(g(a))
  }


  def partial1[A, B, C](a: A, f: (A, B) => C): B => C = {
    (b: B) => f(a, b)
  }
}
