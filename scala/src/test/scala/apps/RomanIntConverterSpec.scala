package apps

import org.scalatest.{FlatSpec, Matchers, WordSpec}

class RomanIntConverterSpec extends FlatSpec with Matchers {
  import RomanIntConverter._
  "Ints to Roman" should "convert single digit" in {
      2.asRoman should be ("II")
      4.asRoman should be ("IV")
      5.asRoman should be ("V")
      9.asRoman should be ("IX")
    }

  it should "convert double digit" in {
      10.asRoman should be ("X")
      20.asRoman should be ("XX")
      24.asRoman should be ("XXIV")
      25.asRoman should be ("XXV")
      29.asRoman should be ("XXIX")
  }

  it should "convert triple digit" in {
    101.asRoman should be ("CI")
    150.asRoman should be ("CL")
    151.asRoman should be ("CLI")
    999.asRoman should be ("CMXCIX")
  }
}
