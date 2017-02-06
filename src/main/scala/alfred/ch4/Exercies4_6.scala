package alfred.ch4

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies4_6 extends App {
  def Try[A](a: => A): Either[Exception, A] = try {
    Right(a)
  } catch {
    case e: Exception => Left(e)
  }
  val res1 = Try { "1".toInt }
  val res2 = Try { "1.2".toInt }
  println("-- Try -- ")
  println(res1)
  println(res2)
  println("-- map --")
  println(res1.map( _ * 2 ))
  println(res2.map( _ * 2 ))
  println("-- flatMap --")
  println(res1.flatMap( a => Right(a * 10)))
  println(res2.flatMap( a => Right(a * 10)))
  println("-- orElse --")
  println(res1.orElse(Right(0.0)))
  println(res2.orElse(Right(0.0)))
  println("-- map2 --")
  println(res1.map2(Right(2))(_ + _))
  println(res2.map2(Right(2))(_ + _))
}
