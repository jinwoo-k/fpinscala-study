package alfred.ch4

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies4_7 extends App {

  def traverse[E,A,B](es: List[A])(f: A => Either[E, B]): Either[E, List[B]] =
    es.foldRight[Either[E,List[B]]](Right(Nil))((a, b) =>  f(a).map2(b)(_ :: _))

  def sequence[E,A](es: List[Either[E,A]]): Either[E,List[A]] = traverse(es)(a => a)

  val s1 = List(Right(1), Right(2), Right(3))
  val s2 = List(Right(1), Left(2), Right(3))
  println(sequence(s1))
  println(sequence(s2))

}
