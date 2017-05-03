package com.mindriot.patterns

object ListUtils {

  implicit class ListUtilities[A](list: Seq[A]) {

    /**
      * Pairs only matching elements of collection A and B, eliminating none matching elements from both collections.
      * Only the first matching element in listB will be paired with element in listA; all other matching elements will be dropped.
      */
    def pairFirstMatching[B, C](other: Seq[B])(fA: A => C, fB: B => C): Seq[(A, B)] = {
      val groupB = other.groupBy(fB)

      list.flatMap{ elem =>
        groupB
          .get(fA(elem))
          .toList
          .flatMap(List(elem).zip(_))
      }
    }

    /**
      * Pairs all elements of collection A with first matching element in collection B.
      */
    def pairBy[B, C](other: Seq[B])(fA: A => C, fB: B => C): Seq[(A, Option[B])] = {
      combine(other)(fA, fB).map(r => (r._1, r._2.headOption))
    }

    /**
      * Combine all elements of collection A with all matching elements in collection B
      */
    def combine[B, C](other: Seq[B])(fA: A => C, fB: B => C): Seq[(A, Seq[B])] = {
      val groupB = other.groupBy(fB)

      list.map{ elem =>
        val value = groupB.getOrElse(fA(elem), Nil)
        (elem, value)
      }
    }

    /**
      * Pair each element of seq A with all matching elements of seq B returning a tuple of the match
      */
    def zipBy[B, C](other: Seq[B])(fA: A => C, fB: B => C): Seq[(A, B)] = {
      def findMatching(a: A, lB: Seq[B]) = {
        val idA = fA(a)
        lB.foldLeft(Seq.empty[(A, B)]) { (acc, b) => if (idA == fB(b)) acc :+ (a, b) else acc }
      }
      list.flatMap(findMatching(_, other))
    }
  }
}