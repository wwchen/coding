package apps

import io.chymyst.jc._

object DiningPhilosophers extends App {
  /** Print message and wait for a random time interval. */
  def wait(message: String): Unit = {
    println(message)
    Thread.sleep(scala.util.Random.nextInt(20))
  }

  val hungry1 = m[Unit]
  val hungry2 = m[Unit]
  val hungry3 = m[Unit]
  val hungry4 = m[Unit]
  val hungry5 = m[Unit]
  val thinking1 = m[Unit]
  val thinking2 = m[Unit]
  val thinking3 = m[Unit]
  val thinking4 = m[Unit]
  val thinking5 = m[Unit]
  val fork12 = m[Unit]
  val fork23 = m[Unit]
  val fork34 = m[Unit]
  val fork45 = m[Unit]
  val fork51 = m[Unit]

  site (
    go { case thinking1(_) => wait("Socrates is thinking");  hungry1() },
    go { case thinking2(_) => wait("Confucius is thinking"); hungry2() },
    go { case thinking3(_) => wait("Plato is thinking");     hungry3() },
    go { case thinking4(_) => wait("Descartes is thinking"); hungry4() },
    go { case thinking5(_) => wait("Voltaire is thinking");  hungry5() },

    go { case hungry1(_) + fork12(_) + fork51(_) => wait("Socrates is eating");  thinking1() + fork12() + fork51() },
    go { case hungry2(_) + fork23(_) + fork12(_) => wait("Confucius is eating"); thinking2() + fork23() + fork12() },
    go { case hungry3(_) + fork34(_) + fork23(_) => wait("Plato is eating");     thinking3() + fork34() + fork23() },
    go { case hungry4(_) + fork45(_) + fork34(_) => wait("Descartes is eating"); thinking4() + fork45() + fork34() },
    go { case hungry5(_) + fork51(_) + fork45(_) => wait("Voltaire is eating");  thinking5() + fork51() + fork45() }
  )
  // Emit molecules representing the initial state:
  thinking1() + thinking2() + thinking3() + thinking4() + thinking5()
  fork12() + fork23() + fork34() + fork45() + fork51()
  // Now reactions will start and print messages to the console.
}
