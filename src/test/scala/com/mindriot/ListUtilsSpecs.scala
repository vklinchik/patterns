package com.mindriot.patterns

import ListUtils._
import org.scalatest.{Matchers, WordSpec}

class ListUtilsSpecs extends WordSpec with Matchers {

  case class TestA(id: Int, value: String)
  case class TestB(index: Int, value: Int)

  val lA = List(
    TestA(4, "one"),
    TestA(5, "two"),
    TestA(6, "three"),
    TestA(7, "seven")
  )

  val lB = List(
    TestB(6, 66),
    TestB(4, 44),
    TestB(5, 55),
    TestB(10, 10)
  )

  val lC = List(
    TestB(4, 66),
    TestB(4, 44),
    TestB(5, 55),
    TestB(10, 10)
  )

  val pairMatchingResponse =
    List(
      (TestA(4, "one"), TestB(4, 44)),
      (TestA(5, "two"), TestB(5, 55)),
      (TestA(6, "three"), TestB(6, 66))
    )

  val pairedResponse =
    List(
      (TestA(4, "one"), Some(TestB(4, 44))),
      (TestA(5, "two"), Some(TestB(5, 55))),
      (TestA(6, "three"), Some(TestB(6, 66))),
      (TestA(7, "seven"), None)
    )

  val pairedResponse2 =
    List(
      (TestA(4, "one"), None),
      (TestA(5, "two"), None),
      (TestA(6, "three"), None),
      (TestA(7, "seven"), None)
    )

  val combinedResponse =
    List(
      (TestA(4, "one"), Seq(TestB(4, 66), TestB(4, 44))),
      (TestA(5, "two"), Seq(TestB(5, 55))),
      (TestA(6, "three"), Nil),
      (TestA(7, "seven"), Nil))

  val emptyA: List[TestA] = Nil
  val emptyB: List[TestB] = Nil

  "ListUtils" should {

    "pair all first matching elements of both collections" in {
      lA.pairFirstMatching(lB)(_.id, _.index) shouldBe pairMatchingResponse
    }

    "pair only first element of another collection if there are duplicates " in {
      lA.pairFirstMatching(lC)(_.id, _.index) shouldBe List((TestA(4, "one"), TestB(4,66)), (TestA(5, "two"),TestB(5,55)))
    }

    "pair all elements from collection A to matching elements of collection B" in {
      lA.pairBy(lB)(_.id, _.index) shouldBe pairedResponse
    }

    "return empty sequence if one of the collections is empty" in {
      emptyA.pairFirstMatching(lB)(_.id, _.index) shouldBe Nil
      lB.pairFirstMatching(emptyA)(_.index, _.id) shouldBe Nil
    }

    "return empty sequence if one of the sequences is empty" in {
      emptyA.pairBy(lB)(_.id, _.index) shouldBe Nil
      lA.pairBy(emptyB)(_.id, _.index) shouldBe pairedResponse2
    }

    "combine all matching elements from the collection A and B" in {
      lA.combine(lC)(_.id, _.index) shouldBe combinedResponse
    }

    "zip all matching elements from the collection A and B" in {
      val zippedResponse =
        List(
          (TestA(4, "one"), TestB(4, 66)),
          (TestA(4, "one"), TestB(4, 44)),
          (TestA(5, "two"), TestB(5, 55))
        )
      lA.zipBy(lC)(_.id, _.index) shouldBe zippedResponse
    }

    "handle zipping empty collections" in {
      emptyA.zipBy(emptyB)(_.id, _.index) shouldBe Nil
      lA.zipBy(emptyB)(_.id, _.index) shouldBe Nil
      emptyA.zipBy(lB)(_.id, _.index) shouldBe Nil
    }
  }
}
