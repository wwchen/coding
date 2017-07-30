package apps

import org.scalatest.WordSpec
import utils.Timer._

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
      val ans = measure(ThreeSum.threeSum(input))
      val result = List(
        List(-10, 4, 6),
        List(-5, -1, 6),
        List(-1, 0, 1),
        List(-5, 1, 4),
        List(-2, 1, 1),
        List(-2, 0, 2)
      )
      assert(ans._1 == result)
    }

    "test 3" in {
      val input = Array(-9,14,-7,-8,9,1,-10,-8,13,12,6,9,3,-3,-15,-15,1,8,-7,-4,-6,8,2,-10,8,11,-15,3,0,-11,-1,-1,10,0,6,5,-14,3,12,-15,-7,-5,9,11,-1,1,3,-15,-5,11,-12,-4,-4,-2,-6,-10,-6,-6,0,2,-9,14,-14,-14,-9,-1,-2,-7,-12,-13,-15,-4,-3,1,14,3,-12,3,3,-10,-9,-1,-7,3,12,-6,0,13,4,-15,0,2,6,1,3,13,8,-13,13,11,11,13,14,-6)
      val ans = measure(ThreeSum.threeSum(input))
      assert(ans._1.nonEmpty)
    }
  }

}
