package alfred.ch5

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies5_11 extends App {

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = f(z) match {
    case Some((a, s)) => Stream.cons(a,unfold(s)(f))
    case None => Stream.empty
  }

  val fibs = unfold((0,1))( s => {
    val (s0,s1) = s
    Some((s0,(s1,s0 + s1)))
  })

  println(fibs.take(10).toList)

}
