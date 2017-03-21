package com.mindriot.fps



object Recursion {

  def factorial(n: Int): Int = {
    @annotation.tailrec
    def loop(seed: Int, acc: Int): Int = {
      if (seed <= 0) {
        acc
      } else {
        loop(seed - 1, seed * acc)
      }
    }
    loop(n, 1)
  }

  /**
    * Generate Fibonacci sequence
    * @param maxSize Max number of values in the returned sequence
    * @return Fibonacci sequence
    */
  def fibonacci(maxSize: Int): Seq[Int] = {
    if (maxSize < 0) throw new IllegalArgumentException("Invalid parameter: maxSize")
    @annotation.tailrec
    def loop(series: Seq[Int]): Seq[Int] = {
      if (series.size >= maxSize) {
        series
      } else {
        val lastTwo = series.takeRight(2)
        val next = lastTwo.head + lastTwo.last
        loop(series :+ next)
      }
    }

    loop(Seq(0, 1))
  }

  /**
    *
    * @param n
    * @return n th Fibonacci number, not counting the seed numbers 0 and 1
    */
  def nthFibonacci(n: Int): Int = {
    if (n < 0) throw new IllegalArgumentException("Invalid parameter")
    @annotation.tailrec
    def loop(n: Int, prev: Int, current: Int): Int = {
      if (n == 0) {
          current
      } else {
        loop(n - 1, current, prev + current)
      }
    }

    loop(n, 0, 1)
  }

  /**
    * Find first occurrence of a string within sequence of strings
    * @param key
    * @param list
    * @return index of a key if found, -1 otherwise
    */
  def findFirstString(key: String, list: Seq[String]): Int = {
    @annotation.tailrec
    def loop(n: Int): Int = {
      if (n >= list.size) {
        -1
      } else if (key == list(n)) {
        n
      }else {
        loop(n  + 1)
      }
    }

    loop(0)
  }

  def findFirst[T](list: Seq[T])(f: T => Boolean): Int = {
    @annotation.tailrec
    def find(n: Int): Int = {
      if (n >= list.size) {
        -1
      } else if (f(list(n))) {
        n
      } else {
        find(n + 1)
      }
    }

    find(0)
  }

  /**
    * Determines if sequence is sorted
    */
  def isSorted[T](seq: Seq[T])(lessThan: (T, T) => Boolean): Boolean = {
    @annotation.tailrec
    def compare(index: Int): Boolean = {
      if (index >= seq.size - 1) {
        true
      } else if (!lessThan(seq(index - 1), seq(index))) {
        false
      } else {
        compare(index + 1)
      }
    }

    compare(1)
  }

}


