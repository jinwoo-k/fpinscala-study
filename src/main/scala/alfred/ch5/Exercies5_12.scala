package alfred.ch5

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies5_12 extends App {

  import Stream._

  val fibs = unfold((0,1))
    { case (s0,s1) => Some((s0,(s1,s0 + s1))) }
  println(fibs.take(10).toList)

  def from(n: Int): Stream[Int] = unfold(n)( s => Some(s,s+1))
  println(from(10).take(5).toList)

  def constant[A](a: A): Stream[A] = unfold(a)( s => Some((s,s)))
  println(constant("A").take(10).toList)

  def ones: Stream[Int] = unfold(1)(s=> Some(s,s))
  println(ones.take(5).toList)
}
