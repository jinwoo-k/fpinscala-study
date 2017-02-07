package alfred.ch4

/**
  * Created by user on 2017-02-06.
  */
sealed trait Either[+E, +A] {
  def map[B](f: A => B): Either[E, B] =
    this match {
      case Right(a) => Right(f(a))
      case Left(e) => Left(e)
    }

  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] =
    this match {
      case Left(e) => Left(e)
      case Right(a) => f(a)
    }

  def orElse[EE >: E, AA >: A](b: => Either[EE, AA]): Either[EE, AA] =
    this match {
      case Left(_) => b
      case Right(a) => Right(a)
    }

  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = for {
    a <- this
    b1 <- b
  } yield f(a,b1)


  def map2_1[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[List[EE], C] =
    (this,b) match {
      case (Right(aa),Right(bb)) => Right(f(aa,bb))
      case (Left(ea),Right(_)) => Left(List(ea))
      case (Right(_),Left(eb)) => Left(List(eb))
      case (Left(ea),Left(eb)) => println(ea);println(eb);Left(List(ea,eb))
    }
}
case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]