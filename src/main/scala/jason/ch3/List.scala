package jason.ch3

import scala.annotation.tailrec

/**
  * Created by jason.kim on 2017. 1. 30..
  */
sealed trait List[+A] {
  // 연습문제 3.2
  def tail: List[A] = this match {
    case Nil => Nil
    case Cons(h, t) => t
  }

  // 연습문제 3.3
  def setHead[B >: A](a: B): List[B] = this match {
    case Nil => Cons(a, Nil)
    case Cons(h, t) => Cons(a, t)
  }
}

case object Nil extends List[Nothing]
case class Cons[+A](h: A, t: List[A]) extends List[A]

object List {

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(h, t) => h + sum(t)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1
    case Cons(h, t) => h * product(t)
  }

  def apply[A](as: A*): List[A] = {
    if (as.isEmpty) {
      Nil
    } else {
      Cons(as.head, apply(as.tail: _*))
    }
  }

  // 연습문제 3.4
  def drop[A](l: List[A], n: Int): List[A] = {
    if (n <= 0) {
      l
    } else {
      drop(l.tail, n - 1)
    }
  }

  // 연습문제 3.5
  def dropWhile[A](l: List[A])(f: A => Boolean): List[A] = l match {
    case Cons(h, t) if f(h) => dropWhile(t)(f)
    case _ => l
  }

  def append[A](a1: List[A], a2: List[A]): List[A] = a1 match {
    case Nil => a2
    case Cons(h, t) => Cons(h, append(t, a2))
  }

  // 연습문제 3.6
  def init[A](l: List[A]): List[A] = l match {
    case Nil | Cons(_, Nil) => Nil
    case Cons(h, t) => Cons(h, init(t))
  }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(h, t) => f(h, foldRight(t, z)(f))
  }

  def sum2(l: List[Int]): Int = foldRight(l, 0)(_ + _)
  def product2(l: List[Double]): Double = foldRight(l, 1d)(_ * _)

  // 연습문제 3.9
  def length[A](l: List[A]): Int = foldRight(l, 0)((a, i) => i + 1)

  // 연습문제 3.10
  @tailrec
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case Cons(h, t) => foldLeft(t, f(z, h))(f)
  }

  // 연습문제 3.11
  def length2[A](l: List[A]): Int = foldLeft(l, 0)((i, a) => i + 1)
  def sum3(l: List[Int]): Int = foldLeft(l, 0)(_ + _)
  def product3(l: List[Double]): Double = foldLeft(l, 1d)(_ * _)

  // 연습문제 3.12
  def reverse[A](l: List[A]): List[A] =
    foldLeft(l, Nil: List[A])((acc, a) => Cons(a, acc))

  // 연습문제 3.13
  def foldLeftViaFoldRight[A, B](as: List[A], z: B)(f: (B, A) => B): B = {
//    foldRight(reverse(as), z)((a, b) => f(b, a))
    foldRight(as, (b: B) => b)((a, g) => b => g(f(b, a)))(z)
  }

  def foldRightViaFoldLeft[A, B](as: List[A], z: B)(f: (A, B) => B): B = {
    foldLeft(as, (b: B) => b)((g, a) => {
      b => g(f(a, b))
    })(z)
  }

  // 연습문제 3.14
  def append2[A](l1: List[A], l2: List[A]): List[A] = {
    foldRight(l1, l2)((a, acc) => Cons(a, acc))
  }

  // 연습문제 3.15
  def flatten[A](ll: List[List[A]]): List[A] = {
    foldRight(ll, Nil: List[A])(append2)
  }

  // 연습문제 3.16
  def plusOne(l: List[Int]): List[Int] =
    foldRight(l, Nil: List[Int])((a, acc) => Cons(a + 1, acc))

  // 연습문제 3.17
  def toString(l: List[Double]): List[String] =
    foldRight(l, Nil: List[String])((a, acc) => Cons(a.toString, acc))

  // 연습문제 3.18
  def map[A, B](l: List[A])(f: A => B): List[B] =
    foldRight(l, Nil: List[B])((a, acc) => Cons(f(a), acc))

  // 연습문제 3.19
  def filter[A](l: List[A])(f: A => Boolean): List[A] =
    foldRight(l, Nil: List[A])((a, acc) => if (f(a)) Cons(a, acc) else acc)

  def filter2[A](l: List[A], f: A => Boolean): List[A] =
    foldRight(l, Nil: List[A])((a, acc) => if (f(a)) Cons(a, acc) else acc)


  // 연습문제 3.20
  def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] =
    flatten(map(l)(f))

  // 연습문제 3.21
  def filterViaFlatMap[A](l: List[A])(f: A => Boolean): List[A] =
    flatMap(l)(a => if (f(a)) List(a) else Nil: List[A])

  // 연습문제 3.22
  def add(l1: List[Int], l2: List[Int]): List[Int] = (l1, l2) match {
    case (Nil, Nil) => Nil
    case (_, Nil) => l1
    case (Nil, _) => l2
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(h1 + h2, add(t1, t2))
  }

  // 연습문제 3.23
  def zipWith[A, B](l1: List[A], l2: List[A])(f: (A, A) => B): List[B] = (l1, l2) match {
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(f(h1, h2), zipWith(t1, t2)(f))
    case _ => Nil
  }


  // 연습문제 3.24
  def startWiths[A](sup: List[A], sub: List[A]): Boolean = (sup, sub) match {
    case (_, Nil) => true
    case (Cons(h1, t1), Cons(h2, t2)) if h1 == h2 => startWiths(t1, t2)
    case _ => false
  }
  @tailrec
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = sup match {
    case Cons(_, t) => if (startWiths(sup, sub)) true else hasSubsequence(t, sub)
    case _ => false
  }


}