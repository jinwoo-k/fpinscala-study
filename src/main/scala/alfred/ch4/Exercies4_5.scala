package alfred.ch4


/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies4_5 extends App {

  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] =
    a.foldRight[Option[List[B]]](Some(Nil))((a,bo) =>
          for {
           fa <- f(a)
           b  <- bo
          } yield (fa :: b)
        )

  val list1 = List("1", "2")
  val list2 = List("1", "2", "false")

  def Try[A](a: => A): Option[A] =
    try Some(a)
    catch {
      case e: Exception => None
    }

  println(traverse(list1)(a => Try[Int](a.toInt)))
  println(traverse(list2)(a => Try[Int](a.toInt)))


  def sequence[A](a: List[Option[A]]): Option[List[A]] = traverse(a)(identity)

  val s1 = List(Some(1), Some(2), Some(3))
  val s2 = List(Some(1), None, Some(3))

  println(sequence(s1))
  println(sequence(s2))

}
