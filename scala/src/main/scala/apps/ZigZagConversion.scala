package apps

// https://leetcode.com/problems/zigzag-conversion
object ZigZagConversion {
  def zigStream(zagLength: Int): Stream[Int] =
    Stream.continually((0 to zagLength) ++ (zagLength - 1 to 1 by -1)).flatten

  def convert(input: String, rows: Int): String =
    zigStream(rows - 1).zip(input).sortBy(_._1).map(_._2).mkString
}
