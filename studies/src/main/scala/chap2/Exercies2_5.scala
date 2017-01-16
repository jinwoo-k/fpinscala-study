package chap2

/**
 * Created by alfredkim on 2017. 1. 16..
 */
object Exercies2_5 extends App {


  /*  연습문제 2-5 (p35) */
  def compose[A,B,C](f: B => C , g : A => B ) : A => C = a => f(g(a))

  def multiTwo(a : Int): Int = a * 2
  def plusOne (b : Int ) : Int =  b + 1

  def multiTwoAndPlusOne = compose(plusOne,multiTwo)
  def plusOneAndMultiTwo = compose(multiTwo,plusOne)
  println(multiTwoAndPlusOne(3)) // (3 * 2) + 1  = 7
  println(plusOneAndMultiTwo(3)) // (3 + 1) * 2  = 8


}
