package apps

sealed trait Tree[+A]

case object EmptyTree extends Tree[Nothing]
case class Node[A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {
  def empty[A]: Tree[A] = EmptyTree
  def node[A](value: A, left: Tree[A] = empty[A], right: Tree[A] = empty[A]) = Node(value, left, right)

  def fold[A, B](t: Tree[A], z: B)(f: (B, A, B) => B): B = t match {
    case EmptyTree => z
    case Node(v, l, r) => f( fold(l, z)(f), v, fold(r, z)(f) )
  }

  def map[A, B](t: Tree[A], f: A => B): Tree[B] = {
    fold(t, Tree.empty[B]) { (l, v, r) => Node(f(v), l, r) }
  }
}