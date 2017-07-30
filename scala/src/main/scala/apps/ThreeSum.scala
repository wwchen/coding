package apps

object ThreeSum extends App {
  /**
    * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
    * Find all unique triplets in the array which gives the sum of zero.
    */
  def threeSumA(nums: Array[Int]): List[List[Int]] = {
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

  def threeSum(nums: Array[Int]): List[List[Int]] = {
    nums.indices.flatMap(i => find3SumPartner(nums(i), nums.drop(i+1))).map(_.toList).toSet.toList
  }

  case class ThreeSum(one: Int, two: Int, three: Int) {
    def toList: List[Int] = List(one, two, three).sorted
    override def equals(obj: scala.Any): Boolean = {
      obj match {
        case o: ThreeSum =>
          Set(one, two, three).equals(o.one, o.two, o.three)
        case _ => false
      }
    }
  }

  def find3SumPartner(one: Int, nums: Array[Int]): List[ThreeSum] = {
    if (nums.isEmpty || nums.length < 2)
      List.empty[ThreeSum]

    val oneSum = ThreeSum(one, 0, 0)
    var threeSums = Set.empty[ThreeSum]

    nums.indices.foldLeft(Map.empty[Int, Set[ThreeSum]]) { case (classifieds, i2) =>
      val two = nums(i2)
      val twoSum = oneSum.copy(two = two)

      val three = -1 * (one + two)

      classifieds.contains(two) match {
        case true =>
          classifieds(two).foreach(p => threeSums += p.copy(three = two))
          classifieds
        case false =>
          val twoSums: Set[ThreeSum] = classifieds.getOrElse(three, Set.empty[ThreeSum]) + twoSum
          classifieds.updated(three, twoSums)
      }
    }
    threeSums.toList
  }

  def breakable(s: String, words: Map[Char, List[String]]): Boolean = {
    if (s.isEmpty) return true

    val c = s.charAt(0)
    words.contains(c) match {
      case false => false
      case true =>
        words(c).foreach { word =>
          //println(word)
          if(s.startsWith(word) && breakable(s.substring(word.length - 1), words))
            return true
        }
        false
    }
  }
}
