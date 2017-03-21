package com.mindriot.fps

package com.mindriot.fps

import org.scalatest.WordSpec
import org.scalatest.Matchers

class HOFSpec extends WordSpec with Matchers {

  "HOF" should {

    def testFunction(x: Int, s: String): String = x.toString + s

    "convert a function of 2 arguments into partially applied function of one argument" in {
      val curry1 = HOF.curry(testFunction)
      val curry2 = HOF.curry2(testFunction)

      curry1(1)("2") shouldBe "12"
      curry2(3)("4") shouldBe "34"
    }

    "reverse transformation of curry" in {
      val curry = HOF.curry(testFunction)
      val uncurry = HOF.uncurry(curry)

      uncurry(1, "2") shouldBe "12"
    }

    "reverse transformation of curry2" in {
      val curry = HOF.curry2(testFunction)
      val uncurry = HOF.uncurry(curry)

      uncurry(3, "4") shouldBe "34"
    }

    "compose two functions" in {
      val g = (x: String) => x.toInt
      val f = (x: Int) => x * 2

      val composed = HOF.compose(f, g)
      //assertResult(44)(composed("22"))
      composed("22") shouldBe 44
    }

    "return function of 1 argument as result" in {
      val f = (x: Int, y: Int ) => x.toString + y.toString
      val partial = HOF.partial1(1, f)
      partial(2) shouldBe "12"
    }
  }

}
