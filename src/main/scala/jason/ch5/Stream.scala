package jason.ch5


/**
  * Created by jason.kim on 2017. 2. 12..
  */
sealed trait Stream[+A] {
  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, t) => Some(h())
  }

  // example 5.1
  def toList: List[A] = this match {
    case Empty => List.empty[A]
    case Cons(h, t) => h() :: t().toList
  }

  // example 5.2 (1)
  def take(n: Int): Stream[A] = this match {
    case Cons(h, t) if n > 0 => Stream.cons(h(), t().take(n - 1))
    case _ => Empty
  }

  // example 5.2 (2)
  def drop(n: Int): Stream[A] = this match {
    case Cons(h, t) if n > 0 => t().drop(n - 1)
    case _ => this
  }

  // example 5.3
  def takeWhile(p: A => Boolean): Stream[A] = this match {
    case Cons(h, t) if p(h()) => Stream.cons(h(), t().takeWhile(p))
    case _ => Empty
  }

  def exists(p: A => Boolean): Boolean = this match {
    case Cons(h, t) => p(h()) || t().exists(p)
    case _ => false
  }

  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
    case Cons(h, t) => f(h(), t().foldRight(z)(f))
    case _ => z
  }

  def existsViaFoldRight(p: A => Boolean): Boolean =
    foldRight(false)((a, b) => p(a) || b)

  // example 5.4
  def forAll(p: A => Boolean): Boolean =
    foldRight(true)((a, b) => p(a) && b)

  // example 5.5
  def takeWhileViaFoldRight(p: A => Boolean): Stream[A] =
    foldRight(Stream.empty[A])((a, b) => if (p(a)) Stream.cons(a, b) else Stream.empty[A])

  // example 5.6
  def headOptionViaFoldRight: Option[A] =
    foldRight(None: Option[A])((a, b) => Some(a))

  // example 5.7
  def map[B](f: A => B): Stream[B] =
    foldRight(Stream.empty[B])((a, b) => Stream.cons(f(a), b))

  def filter(p: A => Boolean): Stream[A] =
    foldRight(Stream.empty[A])((a, b) => if(p(a)) Stream.cons(a, b) else b)

  def append[B >: A](s: Stream[B]): Stream[B] =
    foldRight(s)((a, b) => Stream.cons(a, b))

  def flatMap[B](f: A => Stream[B]): Stream[B] =
    foldRight(Stream.empty[B])((a, b) => f(a).append(b))


  // example 5.13
  def mapViaUnfold[B](f: A => B): Stream[B] =
    Stream.unfold(this) {
      case Cons(h, t) => Some(f(h()), t())
      case Empty => None
    }

  def takeViaUnfold(n: Int): Stream[A] =
    Stream.unfold((n, this)) {
      case (nn, Cons(h, t)) if nn > 0 => Some((h(), (nn - 1, t())))
      case _ => None
    }

  def takeWhileViaUnfold(p: A => Boolean): Stream[A] =
    Stream.unfold(this) {
      case Cons(h, t) if p(h()) => Some((h(), t()))
      case _ => None
    }

  def zipAll[B](that: Stream[B]): Stream[(Option[A], Option[B])] =
    Stream.unfold((this, that)) {
      case (Cons(h1, t1), Cons(h2, t2)) => Some(((Some(h1()), Some(h2())), (t1(), t2())))
      case (Cons(h1, t1), _) => Some(((Some(h1()), None), (t1(), Stream.empty[B])))
      case (_, Cons(h2, t2)) => Some(((None, Some(h2())), (Stream.empty[A], t2())))
      case _ => None
    }

  // example 5.14
  def startsWith[B >: A](that: Stream[B]): Boolean = (this, that) match {
    case (_, Empty) => true
    case (Cons(h1, t1), Cons(h2, t2)) if h1() == h2() => t1().startsWith(t2())
    case _ => false
  }

  def startsWith_answer[B >: A](that: Stream[B]): Boolean =
    this.zipAll(that).takeWhile(_._2.isDefined).forAll(t => t._1 == t._2)

  // example 5.15
  def tails(): Stream[Stream[A]] = {
    Stream.unfold(this) {
      case Empty => None
      case s => Some((s, s.drop(1)))
    }.append(Stream(Stream.empty))
  }

  def hasSubsequence[A](s: Stream[A]): Boolean =
    tails exists(_ startsWith s)

  def scanRight[B](z: B)(f: (A, => B) => B): Stream[B] = {
    foldRight((z, Stream(z)))((a, p) => {
      lazy val p1 = p
      val b = f(a, p1._1)
      (b, Stream.cons(b, p1._2))
    })._2
  }

  def tailsViaScanRight: Stream[Stream[A]] = {
    scanRight(Stream.empty[A])((a, b) => Stream.cons(a, b))
  }

}
case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val h = hd
    lazy val t = tl
    Cons(() => h, () => t)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] = {

    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
  }

  // example 5.8
  def constant[A](a: A): Stream[A] = {
    lazy val tail: Stream[A] = Cons(() => a, () => tail)
    tail
  }

  // example 5.9
  def from(n: Int): Stream[Int] = cons(n, from(n + 1))

  // example 5.10
  def fibs(): Stream[Int] = {
    def f(n1: Int, n2: Int): Stream[Int] = {
      cons(n1 + n2 , f(n2, n1 + n2))
    }
    0 #:: 1 #:: f(0, 1)
  }

  // example 5.11
  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = {
    f(z) match {
      case None => empty[A]
      case Some((a, s)) => cons(a, unfold(s)(f))
    }
  }

  /** A wrapper class that adds `#::` for cons and `#:::` for concat as operations
    *  to streams.
    */
  class ConsWrapper[A](tl: => Stream[A]) {
    /** Construct a stream consisting of a given first element followed by elements
      *  from a lazily evaluated Stream.
      */
    def #::(hd: A): Stream[A] = cons(hd, tl)
    /** Construct a stream consisting of the concatenation of the given stream and
      *  a lazily evaluated Stream.
      */
  }

  /** A wrapper method that adds `#::` for cons and `#::: for concat as operations
    *  to streams.
    */
  implicit def consWrapper[A](stream: => Stream[A]): ConsWrapper[A] =
    new ConsWrapper[A](stream)
}

