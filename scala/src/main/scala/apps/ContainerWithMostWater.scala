package apps

object ContainerWithMostWater extends App {
  /**
    * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
    * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
    * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
    * Note: You may not slant the container and n is at least 2.
    */
  def maxArea(height: Array[Int]): Int = {
    case class Result(maxArea: Int, leftIndex: Int, rightIndex: Int)
    var result = Result(0, 0, height.length - 1)
    while (result.leftIndex < result.rightIndex) {
      val leftHeight = height(result.leftIndex)
      val rightHeight = height(result.rightIndex)
      val area = (result.rightIndex - result.leftIndex) * List(leftHeight, rightHeight).min

      result = Result(
        List(area, result.maxArea).max,
        if (leftHeight > rightHeight) result.leftIndex else result.leftIndex + 1,
        if (leftHeight > rightHeight) result.rightIndex - 1 else result.rightIndex
      )
    }
    result.maxArea
  }
}
