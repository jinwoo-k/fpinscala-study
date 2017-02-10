package alfred.ch5

import Stream._

/**
 * Created by alfredkim on 2017. 2. 8..
 */
trait Stream[+A] {

  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, t) => Some(h())
  }

  def toList: List[A] = {
    val buf = new collection.mutable.ListBuffer[A]
    @annotation.tailrec
    def go(s: Stream[A]): List[A] = s match {
      case Cons(h, t) =>
        buf += h()
        go(t())
      case _ => buf.toList
    }
    go(this)
  }

  def take(n: Int): Stream[A] = this match {
    case Cons(h, t) if n > 1 => cons(h(), t().take(n - 1))
    case Cons(h, _) if n == 1 => apply(h())
    case _ => Empty
  }

  @annotation.tailrec
  final def drop(n: Int): Stream[A] = this match {
    case Cons(_, t) if n > 0 => t().drop(n - 1)
    case _ => this
  }

  def takeWhile(f: A => Boolean): Stream[A] = this match {
    case Cons(h, t) if f(h()) => cons(h(), t().takeWhile(f))
    case _ => Stream.empty
  }

  def foldLeft[B](z: => B)(f: (=> B, A) => B): B = this match {
    case Cons(h, t) => f(t().foldLeft(z)(f), h())
    case _ => z
  }

  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
    case Cons(h, t) => f(h(), t().foldRight(z)(f))
    case _ => z
  }

  def forAll(p: A => Boolean): Boolean =
    foldRight(true)((a, b) => p(a) && b)

  def takeWhile1(f: A => Boolean): Stream[A] =
    foldRight(empty[A])((a, z) => if (f(a)) cons(a, z) else empty)

  def headOption1: Option[A] = foldRight(None: Option[A])((h, _) => Some(h))

  def map[B](f: A => B): Stream[B] = foldRight(empty[B])((a, s) => cons(f(a), s))

  def filter(f: A => Boolean): Stream[A] = foldRight(empty[A])((a, s) => if (f(a)) cons(a, s) else s)

  def append[B >: A](b: => Stream[B]): Stream[B] = foldRight(b)((h, s) => cons(h, s))

  def flatMap[B](f: A => Stream[B]): Stream[B] = foldRight(empty[B])((h, s) => f(h).append(s))

  def find(f: A => Boolean): Option[A] = filter(f).headOption1

  def mapViaUnfold[B](f: A => B): Stream[B] = Stream.unfold(this) {
    case Empty => None
    case Cons(h, t) => Some(f(h()), t())
  }

  def takeViaUnfold(n: Int): Stream[A] =
    unfold((this, n)) {
      case (Cons(h, t), 1) => Some((h(), (empty, 0)))
      case (Cons(h, t), n) if n > 1 => Some((h(), (t(), n - 1)))
      case _ => None
    }

  def takeWhileViaUnfold(f: A => Boolean): Stream[A] =
    unfold((this)) {
      case Cons(h, t) if f(h()) => Some(h(), t())
      case _ => None
    }

  def zipWith[B, C](s2: Stream[B])(f: (A, B) => C): Stream[C] =
    unfold((this, s2)) {
      case (Cons(h1, t1), Cons(h2, t2)) =>
        Some((f(h1(), h2()), (t1(), t2())))
      case _ => None
    }

  def zipAll[B](s2: Stream[B]): Stream[(Option[A], Option[B])] =
    unfold((this, s2)) {
      case (Empty, Empty) => None
      case (Cons(h, t), Empty) => Some((Some(h()), None: Option[B]), (t(), empty[B]))
      case (Empty, Cons(h, t)) => Some((None: Option[A], Some(h())), (empty[A], t()))
      case (Cons(h1, t1), Cons(h2, t2)) => Some((Some(h1()), Some(h2())), (t1(),t2()))
    }
}
case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {

  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))

  def constant[A](a: A): Stream[A] = cons(a,constant(a))

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = f(z) match {
    case Some((a, s)) => Stream.cons(a,unfold(s)(f))
    case None => Stream.empty
  }
}
