package utils

object Timer {
  def measure[A](f: => A): (A, Long) = {
    val startNano = System.nanoTime()
    val ans = f
    val endNano = System.nanoTime()
    val delta = endNano - startNano

    println("Executed in %.3f us".format(delta / 1000f))
    (ans, delta)
  }
}
