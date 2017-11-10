package ridesharing


class KVStore[K, V] {
  object ValueStore {
    def unit: ValueStore = ValueStore(Seq.empty[Int], Map.empty[Int, V])
  }

  case class ValueStore(orderedVersions: Seq[Int], values: Map[Int, V]) {
    def add(value: V, version: Int): ValueStore = {
      // todo order it more efficiently
      ValueStore((orderedVersions :+ version).sorted, values.updated(version, value))
    }

    def get(version: Option[Int]): Option[V] = {
      version match {
        case Some(v) =>
          // todo binary search
          orderedVersions.filter(ov => ov <= v).lastOption.map(values)
        case None =>
          orderedVersions.lastOption.map(values)
      }
    }
  }

  var version = 0
  var store = Map.empty[K, ValueStore]

  def put(key: K, value: V): Int = {
    val version = nextVersion()
    val valueStore = store.getOrElse(key, ValueStore.unit).add(value, version)
    store = store.updated(key, valueStore)
    version
  }

  def get(key: K, version: Option[Int] = None): Option[V] = {
    for {
      vs <- store.get(key)
      v  <- vs.get(version)
    } yield v
  }

  private[this] def nextVersion(): Int = {
    version += 1
    version
  }
}
