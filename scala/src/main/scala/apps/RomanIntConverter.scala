package apps

/**
  * https://leetcode.com/problems/integer-to-roman/description/
  */
object RomanIntConverter {
  implicit class RomanIntConversion(num: Int) {
    private[this] val roman = Seq("I", "V", "X", "L", "C", "D", "M")
    private[this] val romanMap = Map(1 -> "I", 5 -> "V", 10 -> "X", 50 -> "L", 100 -> "C", 500 -> "D", 1000 -> "M")

    def asRoman(pow: Int): String = {
      assert(num < 10)
      val one  = roman(pow * 2 - 2)
      val five = roman(pow * 2 - 1)
      val ten  = roman(pow * 2)
      if (num == 0) ""
      else if (num == 1) one
      else if (num < 4)  one * num
      else if (num == 4) one + five
      else if (num == 5) five
      else if (num == 9) one + ten
      else five + one * (num - 5)
    }

    def asRoman: String = {
      val numS = num.toString.split("")
      val roman = numS.foldLeft(("", numS.length)) { (input, digit) =>
        (input._1 + digit.toInt.asRoman(input._2), input._2 - 1)
      }
      roman._1
    }
  }
}
