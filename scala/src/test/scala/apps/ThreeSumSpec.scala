package apps

import org.scalatest.WordSpec

class ThreeSumSpec extends WordSpec {
  "This" should {
    "equal" in {
      val input = Array(-1, 0, 1, 2, -1, -4)
      val result = List(
        List(-1, 0, 1),
        List(-1, -1, 2)
      )

      assert(ThreeSum.threeSum(input) == result)
    }

    "test 2" in {
      val input = Array(1,-10,-9,-1,6,2,-2,-10,0,2,4,1,-9,-5)
      assert(ThreeSum.threeSum(input).nonEmpty)
    }
  }

}
