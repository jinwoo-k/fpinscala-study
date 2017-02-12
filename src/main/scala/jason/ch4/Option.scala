package jason.ch4

/**
  * Created by jason.kim on 2017. 2. 5..
  */
sealed trait Option[+A] {

  // 연습문제 4.1
  def map[B](f: A => B): Option[B] = this match {
    case Some(a) => Some(f(a))
    case _ => None
  }

  def flatMap[B](f: A => Option[B]): Option[B] = {
    map(f).getOrElse(None)
  }


  def getOrElse[B >: A](default: => B): B = this match {
    case Some(a) => a
    case _ => default
  }

  def orElse[B >: A](ob: => Option[B]): Option[B] = {
    map(a => Some(a)).getOrElse(ob)
  }

  def filter(f: A => Boolean): Option[A] = {
    flatMap { a => if (f(a)) Some(a) else None }
  }

}
case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

object Option {
  def mean(xs: Seq[Double]): Option[Double] = {
    if (xs.isEmpty)
      None
    else
      Some(xs.sum / xs.size)
  }

  // 연습문제 4.2
  def variance(xs: Seq[Double]): Option[Double] = {
    mean(xs).flatMap(m => {
      mean(xs.map(x => math.pow(x - m, 2)))
    })
  }

  // 연습문제 4.3
  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
//    a.flatMap(a => {
//      b.map(b => {
//        f(a, b)
//      })
//    })

    for {
      aa <- a
      bb <- b
    } yield f(aa, bb)
  }


  def map3[A, B, C, D](a: Option[A], b: Option[B], c: Option[C])(f: (A, B, C) => D): Option[D] = {
//    a.flatMap(a => {
//      b.flatMap(b => {
//        c.map(c => {
//          f(a, b, c)
//        })
//      })
//    })

    for {
      aa <- a
      bb <- b
      cc <- c
    } yield f(aa, bb, cc)

  }

  // 연습문제 4.4
  def sequence[A](a: List[Option[A]]): Option[List[A]] = {
    a.foldRight(Some(List.empty[A]): Option[List[A]])((a, o) => (a, o) match {
      case (Some(a), Some(l)) => Some(a :: l)
      case _ => None
    })
  }

  // 연습문제 4.5
  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = {
    a.foldRight[Option[List[B]]](Some(List.empty[B]))((a, o) => {
      map2(f(a), o)(_ :: _)
    })
  }

}


