package alfred.ch5

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies5_3 extends App {

    def intStream(from: Int,to: Int): Stream[Int] =
      if (from < to) Stream.cons(from, intStream(from + 1,to))
      else if (from > to) Stream.cons(from, intStream(from - 1,to))
      else Stream(to)

    val s9  = intStream(1,Int.MaxValue).takeWhile(_ < 10)
    println(s9.forAll(_ < 10))
    println(s9.forAll(_ < 5))

}
