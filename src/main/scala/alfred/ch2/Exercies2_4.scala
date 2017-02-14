package alfred.ch2

/**
 * Created by alfredkim on 2017. 1. 16..
 */
object Exercies2_4 extends App {


  /*  연습문제 2-3 (p34) */
  def curry[A,B,C](f:(A,B) => C ) : A => (B => C) = a => b => f(a,b)

  /*  연습문제 2-4 (p35) */
  def uncurry[A,B,C](f: A => B => C ) : (A,B) => C = (a,b) => f(a)(b)

  def func : Int => String => Char  = curry((a,b) => b.charAt(a))
  def charAt : (Int,String) => Char = uncurry(func)

  println(charAt(0,"kairos"))
  println(charAt(1,"kairos"))

}
