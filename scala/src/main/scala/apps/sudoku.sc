// https://leetcode.com/problems/sudoku-solver/description/

class Counter {
  var count = 0
  def incr = count += 1
}
object Solution {
  implicit class BoardConv(board: Array[Array[Char]]) {
    def getRow(x: Int, y: Int): Array[Char] = board(y)
    def getCol(x: Int, y: Int): Array[Char] = (0 to 8).map(y => board(y)(x)).toArray
    def getSub(x: Int, y: Int): Array[Char] = {
      val xx = x/3*3
      val yy = y/3*3
      (xx to xx+2).flatMap(x => (yy to yy+2).map(y => board(y)(x))).toArray
    }

    def getAll(x: Int, y: Int): Set[Char] = {
      val row = getRow(x, y)
      val col = getCol(x, y)
      val sub = getSub(x, y)
      //println("row is %s\ncol is %s\nsub is %s".format(row.mkString, col.mkString, sub.mkString))
      (row ++ col ++ sub).toSet - '.'
    }

    def getChoices(x: Int, y: Int): Set[Char] = ('1' to '9').toSet -- getAll(x, y)

    def set(x: Int, y: Int, c: Char): Unit = board(y)(x) = c

    // assumes board is in good shape
    def isComplete: Boolean = ! board.exists(_.contains('.'))

    def nextIncomplete: Option[(Int, Int)] = {
      (0 to 8).foreach { y =>
        (0 to 8).foreach { x =>
          if (board(y)(x) == '.') return Some((x, y))
        }
      }
      None
    }

    def nextSmallestIncomplete: Option[(Int, Int)] = {
      val choices: Seq[(Int, Int)] = (0 to 8).flatMap { y =>
        (0 to 8).flatMap { x =>
          if (board(y)(x) == '.') Some((x, y)) else None
        }
      }
      choices.reduceLeftOption { (bestChoice: (Int, Int), choice: (Int, Int)) =>
          if (getChoices(bestChoice._1, bestChoice._2).size >
            getChoices(choice._1, choice._2).size
          ) choice else bestChoice
      }
    }
  }

  def trySolveSudoku(counter: Counter, board: Array[Array[Char]]): Option[Array[Array[Char]]] = {
    val debug = false
    if (debug) println("\n=======\n" + board.map(_.mkString("")).mkString("\n"))
    counter.incr

    board.nextSmallestIncomplete match {
      case Some((x: Int, y: Int)) =>
        val choices = board.getChoices(x, y)
        if (debug && choices.isEmpty) println("for (%s, %s), there are no choices, backing up".format(x, y))
        choices.foreach { c =>
          if (debug) println("for (%s, %s), choices are: %s, picking %s".format(x, y, choices.mkString(", "), c))
          board.set(x, y, c)
          val b = trySolveSudoku(counter, board)
          if (b.isDefined) return b
        }
        board.set(x, y, '.')
        None
      case None => Some(board)
    }
  }

  def solveSudoku(board: Array[Array[Char]]): Unit = {
    var c = new Counter
    var b = board.map(_.clone).clone
    trySolveSudoku(c, b)
    println("Finished the board in %s iterations\n%s".format(c.count, b.map(_.mkString("")).mkString("\n")))
  }
}


val board = Array(
  Array('.','.','9','7','4','8','.','.','.'),
  Array('7','.','.','.','.','.','.','.','.'),
  Array('.','2','.','1','.','9','.','.','.'),
  Array('.','.','7','.','.','.','2','4','.'),
  Array('.','6','4','.','1','.','5','9','.'),
  Array('.','9','8','.','.','.','3','.','.'),
  Array('.','.','.','8','.','3','.','2','.'),
  Array('.','.','.','.','.','.','.','.','6'),
  Array('.','.','.','2','7','5','9','.','.'))

println(Solution.solveSudoku(board))