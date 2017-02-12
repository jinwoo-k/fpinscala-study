package jason.ch4

/**
  * Created by jason.kim on 2017. 2. 5..
  */
sealed trait Either[+E, +A] {
  def map[B](f: A => B): Either[E, B] = this match {
    case Right(a) => Right(f(a))
    case Left(e) => Left(e)
  }

  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match {
    case Right(a) => f(a)
    case Left(e) => Left(e)
  }

  def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
    case Right(a) => Right(a)
    case Left(_) => b
  }

  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = {
    for {
      aa <- this
      bb <- b
    } yield f(aa, bb)
  }

  def map22[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[List[EE], C] = (this, b) match {
    case (Right(a), Right(b)) => Right(f(a, b))
    case (Left(e1), Left(e2)) => Left(List(e1, e2))
    case (_, Left(e)) => Left(List(e))
    case (Left(e), _) => Left(List(e))
  }
}
case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]

object Either {
  def mean(xs: IndexedSeq[Double]): Either[String, Double] = {
    if (xs.isEmpty)
      Left("mean of empty list!")
    else
      Right(xs.sum / xs.size)
  }

  def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] =
    traverse(es)(a => a)

  def traverse[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]] = {
    as.foldRight[Either[E, List[B]]](Right(List.empty[B]))((ea, el) => f(ea).map2(el)(_ :: _))
  }

}
