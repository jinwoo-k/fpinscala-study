package alfred.ch5

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies5_9 extends App {

  def from(n: Int): Stream[Int] = Stream.cons(n,from(n+1))
  println(from(2).take(5))
}
