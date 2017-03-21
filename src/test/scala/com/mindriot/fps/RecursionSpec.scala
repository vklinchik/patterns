package com.mindriot.fps

import org.scalatest.WordSpec

class RecursionSpec extends WordSpec {

  "Recursive" should {
    "compute factorial" in {
      val factorial = Recursion.factorial(5)
      assert(factorial == 120)
    }

    "compute Fibonacci" in {
      val validResult = Seq(0, 1, 1, 2, 3, 5)
      assert(Recursion.fibonacci(6) == validResult)
    }

    "return n th Fibonacci number" in {
      assertResult(3)(Recursion.nthFibonacci(3))
    }

    "throw an exception when number is negative" in {
      assertThrows[IllegalArgumentException](Recursion.nthFibonacci(-1))
    }

    "find first key occurrence in the string list" in {
      val testList = List("one", "two", "three", "three", "four")
      assert(Recursion.findFirstString("three", testList) == 2)
    }

    "find first occurrence of value in the list" in {
      val stringList = List("one", "two", "three", "three", "four")
      val intList = List(1, 2, 3, 3, 4)
      assertResult(2)(Recursion.findFirst(stringList)(_ == "three"))
      assertResult(2)(Recursion.findFirst(intList)(_ == 3))
    }

    "determine if sequence is sorted" in {
      val sortedIntSeq = List(1, 2, 3, 4, 5)
      val unsortedIntSeq = List(3, 4, 1, 5)
      val sortedStringSeq = List("a", "b", "c")
      val unsortedStringSeq = List("e", "a", "b")

      val compareInt: (Int, Int) => Boolean = {(left, right) => left < right}
      val compareStr: (String, String) => Boolean = {(left, right) => left < right}

      assertResult(true)(Recursion.isSorted(sortedIntSeq)(compareInt))
      assertResult(false)(Recursion.isSorted(unsortedIntSeq)(compareInt))
      assertResult(true)(Recursion.isSorted(sortedStringSeq)(compareStr))
      assertResult(false)(Recursion.isSorted(unsortedStringSeq)(compareStr))
    }
  }

}
