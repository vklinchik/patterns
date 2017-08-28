package com.mindriot.fps

import org.scalatest.WordSpec
import org.scalatest.Matchers

import com.mindriot.fps.DataStructures._

class DataStructuresSpec extends WordSpec with Matchers {

  "DataStructures" should {

    "compute list sum" in {
      val l: List[Int] = List[Int](0, 1, 2, 3, 4, 5)
      List.sum(l) shouldBe 15
    }

    "compute list product" in {
      val l = List[Double](3.0, 3.0, 3.0)
      List.product(l) shouldBe 27.0
    }

    "fill list with a given value" in {
      val l = List(0, 0, 0, 0, 0)
      List.fill(0, 5) shouldBe l
    }

    "remove first element of the list" in {
      val l = List(0, 1, 2, 3, 4)
      List.tail(l) shouldBe List(1, 2, 3, 4)
      List.tail(Nil) shouldBe Nil
    }

    "set head element of the list" in {
      val l = List(0, 1, 2, 3, 4)
      List.setHead(-1, l) shouldBe List(-1, 0, 1, 2, 3, 4)
      List.setHead(0, Nil) shouldBe List(0)
    }

    "drop number of elements from the list" in {
      val l = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
      List.drop(l, 5) shouldBe List(5, 6, 7, 8, 9)
      List.drop(Nil, 3) shouldBe Nil
      List.drop(l, -1) shouldBe Nil
      List.drop(l, 20) shouldBe Nil
    }

    "drop leading elements from the list that satisfy predicate" in {
      val l = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
      val predicate = (x: Int) => if (x < 5) true else false

      List.dropWhile(l)(predicate) shouldBe List(5, 6, 7, 8, 9)
      List.dropWhile(Nil)(predicate) shouldBe Nil
      List.dropWhile(l)(x => if (x > 10) true else false) shouldBe l
    }

    "append one list to another" in {
      val l1 = List(0, 1, 2, 3, 4, 5)
      val l2 = List(6, 7, 8, 9)

      List.append(l1, l2) shouldBe List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
      List.append(l1, Nil) shouldBe l1
      List.append(Nil, l2) shouldBe l2
    }

    "return list consisting all but last element" in {
      val list = List(0, 1, 2, 3, 4, 5)
      List.init(list) shouldBe List(0, 1, 2, 3, 4)
      List.init(Nil) shouldBe Nil
      List.init(List(0)) shouldBe Nil
    }

    "fold right over collection" in {
      val f = (x: Int, y: Int) => x + y
      List.foldRight(List(0, 1, 2, 3, 4, 5), 0)(f) shouldBe 15
      List.foldRight(Nil, 0)(f) shouldBe 0
      List.foldRight(List(1), 0)(f) shouldBe 1
    }

    "fold left over collection" in {
      val f = (x: Int, y: Int) => x + y
      List.foldLeft(List(0, 1, 2, 3, 4, 5), 0)(f) shouldBe 15
      List.foldLeft(Nil, 0)(f) shouldBe 0
      List.foldLeft(List(1), 0)(f) shouldBe 1
    }

    "compute length of the list" in {
      List.length(List(1, 2, 3, 4, 5)) shouldBe 5
      List.length(Nil) shouldBe 0
    }

    "compute list sum2" in {
      val l: List[Int] = List[Int](0, 1, 2, 3, 4, 5)
      List.sum2(l) shouldBe 15
    }

    "compute list product2" in {
      val l = List[Double](3.0, 3.0, 3.0)
      List.product2(l) shouldBe 27.0
    }

    "compute length2 of the list" in {
      List.length2(List(1, 2, 3, 4, 5)) shouldBe 5
      List.length2(Nil) shouldBe 0
    }

    "reverse list" in {
      List.reverse(List(1, 2, 3, 4, 5)) shouldBe List(5, 4, 3, 2, 1)
      List.reverse(Nil: List[Int]) shouldBe Nil
    }

    "pattern match" in {
      val x =
        List(1, 2, 3, 4, 5) match {
          case Cons(x, Cons(2, Cons(4, _))) => x
          case Nil => 42
          case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
          case Cons(h, t) => h + List.sum(t)
          case _ => 101
      }

      x shouldBe 3
    }
  }
}
