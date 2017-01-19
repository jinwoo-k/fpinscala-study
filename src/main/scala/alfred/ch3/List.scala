package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */
sealed trait List[+A]

case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x,xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  // p46
  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }

  // 연습문제 3.1
  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }

  // 연습문제 3.2
  def tail[A](l : List[A]) : List[A] =  l match {
    case Nil => sys.error("tail of empty list")
    case Cons(h,t) => t
  }

  // 연습문제 3.3
  def setHead[A](l: List[A], h: A) : List[A] = l match {
    case Nil => sys.error("setHead on empty list")
    case Cons(_,t) => Cons(h, t)
  }

  // 연습문제 3.4
  def drop[A](l: List[A], n: Int) : List[A] = l match {
      case Cons(_,t) if(n > 0) => drop(t, n-1)
      case _ => l
  }

  // 연습문제 3.5
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] =
    l match {
      case Cons(h,t) if f(h) => dropWhile(t, f)
      case _ => l
    }

  def dropWhile2[A](l: List[A])(f: A => Boolean): List[A] =
    l match {
      case Cons(h,t) if f(h) => dropWhile(t, f)
      case _ => l
    }

  // 연습문제 3.6
  def init[A](l: List[A]): List[A] =
    l match {
      case Nil => sys.error("init of empty list")
      case Cons(_,Nil) => Nil
      case Cons(h,t) => Cons(h,init(t))
    }

  // 연습문제 3.7 - 긴 목록 처리시에 스택이 넘칠 수 있다.
  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  // 연습문제 3.7
  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _)

  // 연습문제 3.9
  def length[A](as: List[A]) : Int = foldRight(as,0)((_,len) => len+1 )

  // 연습문제 3.10
  @annotation.tailrec
  def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B = l match {
    case Nil => z
    case Cons(h,t) => foldLeft(t, f(z,h))(f)
  }

  // 연습문제 3.12
  def reverse[A](l: List[A]) : List[A] = foldLeft(l,Nil:List[A])((r,a) => Cons[A](a,r))

  // 연습문제 3.13  foldLeft를 이용하여 구현하므로 스택이 넘치는 현상을 피할 수 있다
  def foldRight2[A,B](l: List[A], z: B)(f: (A,B) => B): B = foldLeft(l, (b:B) => b)((g,a) => b => g(f(a,b)))(z)
}
