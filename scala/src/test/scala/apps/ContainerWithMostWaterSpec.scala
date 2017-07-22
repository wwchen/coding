package apps

import org.scalatest.WordSpec

class ContainerWithMostWaterSpec extends WordSpec {
  "zero height" should {
    "be empty" in {
      val heights = Array(0, 1)
      assert(ContainerWithMostWater.maxArea(heights) == 0)
    }
  }

  "two heights" should {
    "be it" in {
      val heights = Array(1, 1)
      assert(ContainerWithMostWater.maxArea(heights) == 1)
    }
  }
}
