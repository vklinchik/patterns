package com.mindriot.fps


object DataStructures {

  sealed trait List[+A]
  case object Nil extends List[Nothing]
  case class Cons[+A](head: A, tail: List[A]) extends List[A]


  object List {

    def apply[A](as: A*): List[A] = {
      if (as.isEmpty) Nil else Cons(as.head, apply(as.tail: _*))
    }

    def fill[A](value: A, size: Int): List[A] = {
      if (size == 0) Nil else Cons(value, fill(value, size - 1))
    }

    def tail[A](list: List[A]): List[A] = {
      list match {
        case Cons(_, tail) => tail
        case _ => Nil
      }
    }

    def setHead[A](head: A, list: List[A]): List[A] = {
      Cons(head, list)
    }

    def drop[A](list: List[A], count: Int): List[A] = {
      list match {
        case Cons(_, tail) if count == 0 => list
        case Cons(_, tail) if count > 0 => drop(tail, count - 1)
        case _ => Nil
      }
    }

    def dropWhile[A](list: List[A])(f: A => Boolean): List[A] = {
      list match {
        case Cons(head, tail) if f(head) => dropWhile(tail)(f)
        case _ => list
      }
    }

    def append[A](list1: List[A], list2: List[A]): List[A] = {
      list1 match {
        case Nil => list2
        case Cons(head, tail) => Cons(head, append(tail, list2))
      }
    }

    /**
      * Return list consisting all but last element
      */
    def init[A](list: List[A]): List[A] = {
      list match {
        case Cons(_, Nil) => Nil
        case Cons(head, tail) => Cons(head, init(tail))
        case _ => list
      }
    }

    def foldRight[A, B](list: List[A], initVal: B)(f: (A, B) => B): B = {
      list match {
        case Nil => initVal
        case Cons(head, tail) => f(head, foldRight(tail, initVal)(f))
      }
    }

    @annotation.tailrec
    def foldLeft[A, B](list: List[A], initVal: B)(f: (B, A) => B): B = {
      list match {
        case Nil => initVal
        case Cons(head, tail) => foldLeft(tail, f(initVal, head))(f)
      }
    }

    /*
    def foldRightViaFoldLeft[A, B](list: List[A], initVal: B)(f: (A, B) => B): B = {
      foldLeft(list, (b:B) => b)( (g,a) => b => g(f(a,b)))(initVal)
    } */


    def reverse[A](list: List[A]): List[A] = foldLeft(list, Nil: List[A])((acc, lst) => Cons(lst, acc))


    def sum(values: List[Int]): Int = foldRight(values, 0)(_ + _)

    def sum2(values: List[Int]): Int = foldLeft(values, 0)(_ + _)

    def product(values: List[Double]): Double = foldRight(values, 1.0)(_ * _)

    def product2(values: List[Double]): Double = foldLeft(values, 1.0)(_ * _)

    def length[A](list: List[A]): Int = foldRight(list, 0)((_, counter) => counter + 1)

    def length2[A](list: List[A]): Int = foldLeft(list, 0)((acc, _) => acc + 1)

  }
}
