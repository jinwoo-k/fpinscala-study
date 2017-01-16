package chap2

/**
 * Created by alfredkim on 2017. 1. 16..
 */
object Exercies2_3 extends App {

  /*  연습문제 2-3 (p34) */
  def curry[A,B,C](f:(A,B) => C ) : A => (B => C) = a => b => f(a,b)

  def firstChar = curry[Int,String,Char]{  (a,b) => b.charAt(a) }(0)
  def secondChar = curry[Int,String,Char]{  (a,b) => b.charAt(a) }(1)

  println(firstChar("kairos"))
  println(secondChar("kairos"))

}
