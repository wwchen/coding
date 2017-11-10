package ridesharing

import scala.io.Source

object VersionedKeyValueStoreRunner extends App {
  val dir = "versioned-kv-store-sample-files"
  val inputFiles = Seq("input.txt")

  inputFiles.foreach { filename =>
    val kvClient = new KVStoreClient
    Source.fromFile(s"$dir/$filename").getLines.foreach { line =>
      val result = kvClient.execute(line)
      println(result)
    }
  }
}
