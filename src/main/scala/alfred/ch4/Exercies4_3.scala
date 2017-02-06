package alfred.ch4

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies4_3 extends App {
    def lift[A,B](f: A => B): Option[A] => Option[B] = _ map f

    def abs  = lift(Math.abs)
    println(abs(Some(-1)))

    def sine = lift(Math.sin)
    println(sine(Some(Math.PI/2)))

    println("-- 부분함수 even")
    def even: PartialFunction[Int, Boolean] = { case i if i > 0 => i % 2 == 0}
    println(even.isDefinedAt(0))
    println(even(1))
    println(even(2))
    //println(even(1))
    println((-5 to 10).collect(even))

    println("-- 승격된 함수 even.lift")
    println(even.lift(1))
    println(even.lift(2))
    println(even.lift(0))

    println("-- map2 --")
    def map2[A,B,C](a: Option[A], b: Option[B])(f: (A,B) => C) : Option[C] = (a, b) match {
        case (Some(aa),Some(bb)) => Some(f(aa,bb))
        case  _ => None
    }
    println(map2(Some(1),Some(2))(_ * _))

}
