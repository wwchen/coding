package ridesharing

import org.scalatest.{FlatSpec, Matchers}

class KVStoreSpec extends FlatSpec with Matchers {
  "KVStore" should "get latest" in {
    val store = new KVStore[String, Int]
    val key = "key"
    store.put(key, 1)
    store.put(key, 2)
    store.put(key, 3)
    store.get(key) should equal(Some(3))
    store.get(key, Some(5)) should equal(Some(3))
  }
}
