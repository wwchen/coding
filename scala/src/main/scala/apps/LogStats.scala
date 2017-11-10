package apps

import org.apache.commons.io.input._

import scala.io.Source
import scala.reflect.io.{File, Path}
import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class LogStats {
  val file = Source.fromFile("asdf")
  val tailer = new Tailer(Path("asdf").jfile, new TailerListener)


  var s = Map.empty[Int, Int]
  val g = s - 2
var gg = ArrayBuffer.empty[Int]
  gg.clear()
  Random.nextInt(10)
  file.getLines
  tailer.run()
}

class TailerListener extends TailerListenerAdapter {
  override def handle(line: String) = {
    println("gotten this line")
  }
}