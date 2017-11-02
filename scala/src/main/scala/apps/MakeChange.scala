package apps

import scala.collection.mutable

class MakeChange {
  val denominations = Seq(1, 5, 10, 25)

  private[this] val combinations = mutable.Map.empty[Int, Set[Seq[Int]]]

  def getCombinations(amount: Int): Set[Seq[Int]] = {
    if (combinations.contains(amount))
      combinations(amount)
    else {
      val combos = denominations.filter(d => amount - d >= 0).flatMap { d =>
        val combo = getCombinations(amount - d).map(_ :+ d).map(_.sorted)
        if (combo.isEmpty) Set(Seq(d)) else combo
      }.toSet
      combinations.update(amount, combos)
      combos
    }
  }

  def makeChange(value: Int, denominations: Seq[Int]): Map[Int, Int] = {
    val denoms = denominations.sortWith(_ > _)
    println("Denominations: " + denoms.mkString(", "))
    val changes = denoms.indices.map { i =>
      val ddenoms = denoms.drop(i)
      ddenoms.foldLeft((value, Map.empty[Int, Int])) { (t, d) =>
        (t._1 % d, t._2 ++ Map(d -> t._1 / d))
      }._2.filter(_._2 > 0)
    }
    changes.sortWith(_.values.sum < _.values.sum).head
  }

  makeChange(43, Seq(25, 10, 5, 1))
  makeChange(40, Seq(25, 20, 10, 5, 1))
  makeChange(53, Seq(25, 20, 10, 5, 1))
}
