package apps

object ThreeSum extends App {
  /**
    * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
    * Find all unique triplets in the array which gives the sum of zero.
    */
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    type Tuple = List[Int]
    type TupleWithIndices = (List[Int], Set[Int])
    val twoSum = nums.indices.foldLeft(Map.empty[Int, Set[TupleWithIndices]]) { case (map, i1) =>
      val one = nums(i1)
      (i1 + 1 until nums.length).foldLeft(map) { case (map, i2) =>
          val two = nums(i2)
          val sum = one + two
          val existingTuples = map.getOrElse(sum, Set.empty[TupleWithIndices])
          val newTuple = (List(one, two).sorted, Set(i1, i2))
          map.updated(sum,  existingTuples + newTuple)
      }
    }

    println(twoSum)

    val threeSum = nums.indices.foldLeft(Set.empty[List[Int]]) { case (result, i) =>
        val num = nums(i)
        val sumToFind = 0 - num
        twoSum.get(sumToFind) match {
          case Some(x) =>
            x.foldLeft(result) { case (result, twoSum) =>
              if (!twoSum._2.contains(i)) {
                result + (twoSum._1 :+ num).sorted
              } else {
                result
              }

            }
          case None => result
        }
    }

    threeSum.toList
  }

}
