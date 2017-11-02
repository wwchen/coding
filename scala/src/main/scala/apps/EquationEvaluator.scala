package apps

object EquationEvaluator {
  case class Token(value: String)

  sealed trait Operator

  case object Multiply

  case object Add extends Operator {
    override def perform(one: Int, two: Int): Int = one + two
  }

  /**
    * Assume:
    * - valid expression
    * - contains only positive ints
    * - + and * operations
    */
  def eval(expression: String): Int = {
    val tokens = expression.split("[^0-9\\+\\*]")
    println(tokens.mkString(", "))

    val afterMult = tokens.foldLeft((Seq.empty[String], Seq.empty[String])) { case (tuple, token) =>
      val buffer = tuple._2 :+ token
      val tokens = tuple._1 ++ {
        if (buffer.size == 3 && buffer(1) == "*") Seq((buffer(0).toInt * buffer(2).toInt).toString)
        else buffer
      }
      (tokens, if(buffer.size == 3) Seq.empty else buffer)
    }

    println(afterMult)

    val afterAdd = afterMult._1.foldLeft((Seq.empty[String], Seq.empty[String])) { case (tuple, token) =>
      val buffer = tuple._2 :+ token
      val tokens = tuple._1 ++ {
        if (buffer.size == 3 && buffer(1) == "+") Seq((buffer(0).toInt + buffer(2).toInt).toString)
        else buffer
      }
      (tokens, if(buffer.size == 3) Seq.empty else buffer)
    }

    Map().get

    afterAdd._1.head.toInt
  }

}
