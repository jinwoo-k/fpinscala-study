package alfred.ch5

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies5_2 extends App {

    def intStream(from: Int,to: Int): Stream[Int] =
      if (from < to) Stream.cons(from, intStream(from + 1,to))
      else if (from > to) Stream.cons(from, intStream(from - 1,to))
      else Stream(to)

    val s  = intStream(1,Int.MaxValue)
    val s10  = s.take(10)
    println(s10)
    println(s10.drop(5))
    println(s10)
    println(s10.drop(5))
}
