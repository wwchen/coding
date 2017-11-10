package ridesharing

class KVStoreClient {
  val store = new KVStore[String, Int]

  // todo change return type from String to Either[Exception, String]
  def execute(command: String): String = {
    val tokens = command.split(' ')
    tokens.head match {
      case "PUT" =>
        val key = tokens(1)
        val value = tokens(2).toInt
        val version = store.put(key, value)
        "PUT(#%s) (%s) = %s".format(version, key, value)
      case "GET" =>
        val key = tokens(1)
        val version = if (tokens.length >= 3) Some(tokens(2).toInt) else None
        val value = store.get(key, version)
        version match {
          case Some(v) =>
            "GET %s(#%s) = %s".format(key, v, value.getOrElse("<NULL>"))
          case None =>
            "GET %s = %s".format(key, value.getOrElse("<NULL>"))
        }
      case _ =>
        throw new IllegalArgumentException(s"ERROR: Invalid command: $command")
    }
  }
}
