
case class X(id: Int)
case class Y(id: String)

val lA = List(X(1), X(2), X(3))
val lB = List(Y("1"), Y("3"), Y("4"))

/*
def zipBy[A, B, T](listA: Seq[A], listB: Seq[B])(fA: A => T, fB: B => T): Seq[(A, B)] = {

  listA.map{ valueA =>

  }

  val x: Seq[(A, B)] =
    for{
      a <- lA
      b <- lB if fA(a) == fB(b)
    } yield (a, b)

  x
}
*/

def zipBy[A, B, C](listA: Seq[A], listB: Seq[B])(fA: A => C, fB: B => C): Seq[(A, B)] = {

  def pair(a: A, lB: Seq[B]) = {
    val idA = fA(a)
    lB.foldLeft(Seq.empty[(A, B)]) { (acc, b) =>
      if (idA == fB(b)) acc :+ (a, b) else acc
    }
  }

  listA.flatMap(pair(_, listB))
}



zipBy(lA, lB)(_.id.toString, _.id)


val l1 = (0 to 10000)
val l2 = (0 to 20000)


val zipped = zipBy(l1, l2)(identity, identity)
zipped.size