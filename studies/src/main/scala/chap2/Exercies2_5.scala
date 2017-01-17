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
  def plusOneAndplusOne  = compose(plusOne,plusOne)
  def multiTwoAndmultiTwo= compose(multiTwo,multiTwo)

  println(multiTwoAndPlusOne(3)) // (3 * 2) + 1  = 7
  println(plusOneAndMultiTwo(3)) // (3 + 1) * 2  = 8
  println(plusOneAndplusOne(3)) // (3 + 1) + 1 = 5
  println(multiTwoAndmultiTwo(3)) // (3 * 2) * 2 = 12

  // 중위 연산자 표현 (** 위에서 선언한 compose 함수가 아님!!!)
  println ((plusOne _ compose multiTwo _)(3))
  println ((multiTwo _ compose plusOne _)(3))
  println ((plusOne _ compose plusOne _)(3))
  println ((multiTwo _ compose multiTwo _)(3))
}
