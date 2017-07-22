package apps

import org.scalatest.{FlatSpec, WordSpec}

class ZigZagConversionSpec extends WordSpec {
  "PAYPALISHIRING" should {
    "convert to PAHNAPLSIIGYIR" in {
      assert(ZigZagConversion.convert("PAYPALISHIRING", 3).equals("PAHNAPLSIIGYIR"))
    }
  }

  "Empty string" should {
    "be empty" in {
      assert(ZigZagConversion.convert("", 1).equals(""))
    }
  }
}
