package jason.ch3


/**
  * Created by jason.kim on 2017. 1. 31..
  */
sealed trait Tree[+A] {

  // 연습문제 3.25
  def size(): Int = this match {
    case Branch(l, r) => l.size + r.size + 1
    case _ => 1
  }

  // 연습문제 3.27
  def depth(): Int = this match {
    case Branch(l, r) => (l.depth max r.depth) + 1
    case _ => 1
  }

  // 연습문제 3.28
  def map[B](f: A => B): Tree[B] = this match {
    case Branch(l, r) => Branch(l.map(f), r.map(f))
    case Leaf(a) => Leaf(f(a))
  }

  // 연습문제 3.29
  def fold[B](f: A => B)(g: (B, B) => B): B = this match {
    case Branch(l, r) => g(l.fold(f)(g), r.fold(f)(g))
    case Leaf(a) => f(a)
  }

  def sizeViaFold(): Int = fold(_ => 1)(_ + _ + 1)

  def depthViaFold(): Int = fold(_ => 1)((l, r) => (l max r) + 1)

  def mapViaFold[B](f: A => B): Tree[B] =
    fold(a => Leaf(f(a)): Tree[B])((l, r) => Branch(l, r))

}

case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {

  // 연습문제 3.26
  def maximum(t: Tree[Int]): Int = t match {
    case Leaf(n) => n
    case Branch(l, r) => maximum(l).max(maximum(r))
  }

  def maximumViaFold(t: Tree[Int]): Int =
    t.fold(a => a)((l, r) => l max r)
}