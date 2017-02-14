package alfred.ch5

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies5_10 extends App {

  val fibs : Stream[Int] = {
    def go(f0: Int, f1: Int) : Stream[Int] = Stream.cons(f0,go(f1,f0+f1))
    go(0,1)
  }

  println(fibs.take(10))

}
