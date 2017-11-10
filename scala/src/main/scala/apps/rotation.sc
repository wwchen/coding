def rotationIndex(arr: Seq[Int], left: Int, right: Int): Int = {
  if (left >= right) return left
  val mid = (left + right) / 2

  val isLeftOrdered = arr(left) < arr(mid)
  val isRightOrdered = arr(mid) < arr(right)

  println("%s %s %s".format(left, mid, right))

  def searchLeft = {
    if (left == mid - 1)
      if (isLeftOrdered) left else mid
    else if (right == mid + 1)
      if (isRightOrdered) right + 1 else mid
    else
      rotationIndex(arr, left, mid - 1)
  }
  def searchRight = {
    if (left == mid - 1)
      if (isLeftOrdered) left else mid
    else if (right == mid + 1)
      if (isRightOrdered) right + 1 else mid
    else
      rotationIndex(arr, mid + 1, right)
  }

  if (isLeftOrdered && !isRightOrdered)
    searchRight
  else if (!isLeftOrdered && isRightOrdered)
    searchLeft
  else
    left
}
def findRotation(arr: Seq[Int]) = rotationIndex(arr, 0, arr.length -1)

println(findRotation(Seq(4,5,8,1,2)) == 3)
println(findRotation(Seq(1,2,3,4,5)) == 0)
println(findRotation(Seq()) == 0)
println(findRotation(Seq(5,1,2,3)) == 1)
println(findRotation(Seq(5,6,1,2,3)) == 2)
println(findRotation(Seq(5,6,7,1,2,3)) == 3)
println(findRotation(Seq(5,6,7,8,1,2,3)) == 4)
println(findRotation(Seq(5,6,7,8,1,2)) == 4)

val i = scala.io.Source.fromFile("/Users/wchen/git/coding/scala/test").getLines()
while(i.hasNext) {
  println(i.next())
  Thread.sleep(1000)
}