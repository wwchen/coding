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
}
