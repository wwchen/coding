package apps

import org.scalatest.{FlatSpec, Matchers}

class MakeChangeSpec extends FlatSpec with Matchers {
  "make change" should "make change" in {
    val changer = new MakeChange
    changer.getCombinations(0) should be (Set.empty[Int])
    changer.getCombinations(5) should be (Set(Seq(1,1,1,1,1), Seq(5)))
    changer.getCombinations(9) should be (Set(Seq(1,1,1,1,1,1,1,1,1), Seq(1,1,1,1,5)))
    changer.getCombinations(11) should be (Set(Seq(1,1,1,1,1,1,1,1,1,1,1), Seq(1,1,1,1,1,1,5), Seq(1,5,5), Seq(1,10)))
  }
}
