package alfred.ch4

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies4_2 extends App {
    def lift[A,B](f: A => B): Option[A] => Option[B] = _ map f

    def abs  = lift(Math.abs)
    println(abs(Some(-1)))

    def sine = lift(Math.sin)
    println(sine(Some(Math.PI/2)))

    val even: PartialFunction[Int, Boolean] = { case i if i > 0 => i % 2 == 0}
    println(even.isDefinedAt(0))
    //println(even(0))
    println(even.lift(0))

}
