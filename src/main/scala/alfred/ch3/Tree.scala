package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 20..
 */
sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A],right: Tree[A]) extends Tree[A]

object Tree {

  // 연습문제 3.25
  def size[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(l, r) => 1 + size(l) + size(r)
  }

  // 연습문제 3.26
  def maximum(tree: Tree[Int]): Int = tree match {
    case Leaf(v) => v
    case Branch(l, r) => Math.max(maximum(l), maximum(r))
  }

  // 연습문제 3.27
  def depth[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 0
    case Branch(l, r) => 1 + Math.max(depth(l), depth(r))
  }

  // 연습문제 3.28
  def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
    case Leaf(a) => Leaf(f(a))
    case Branch(l, r) => Branch(map(l)(f), map(r)(f))
  }

  // 연습문제 3.29
  def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
    case Leaf(a) => f(a)
    case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
  }

  def size1[A](t: Tree[A]): Int = fold(t)(a => 1)(1 + _ + _)

  def maximum1(t: Tree[Int]): Int = fold(t)(a => a)(Math.max(_, _))

  def depth1[A](t: Tree[A]): Int = fold(t)(a => 0)((l, r) => 1 + Math.max(l, r))

  def map1[A, B](t: Tree[A])(f: A => B): Tree[B] = fold(t)(a => Leaf(f(a)): Tree[B])(Branch(_, _))
}
