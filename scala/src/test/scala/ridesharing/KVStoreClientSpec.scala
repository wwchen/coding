package ridesharing

import org.scalatest.{FlatSpec, Matchers}

class KVStoreClientSpec extends FlatSpec with Matchers {
  val kvClient = new KVStoreClient

  "Client" should "throw exception for illegal commands" in {
    an [Exception] should be thrownBy kvClient.execute("GET key1 2.1")
    an [Exception] should be thrownBy kvClient.execute("GET key1")
    an [Exception] should be thrownBy kvClient.execute("PUT key1 foo")
  }

  "Client" should "accept PUT commands" in {
    kvClient.execute("PUT key1 12") should equal("PUT(#1) (key1) = 12")
  }

  "Client" should "accept GET commands" in {
    kvClient.execute("GET key1") should equal("GET key1 = 12")
    kvClient.execute("GET key1 3") should equal("GET key1(#1) = 12")
  }
}
