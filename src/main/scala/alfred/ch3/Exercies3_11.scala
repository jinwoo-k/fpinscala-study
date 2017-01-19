package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.11 (p52)
object Exercies3_11 extends App {

  def sum(l: List[Int]): Int = List.foldLeft(l,0)(_ + _)

  def product(l: List[Int]): Double = List.foldLeft(l,1.0)(_ * _)

  def length[A](as: List[A]) : Int = List.foldLeft(as,0)((len,_) => len+1 )

  val list = List(1,2,3,4,5,6,7,8,9,10)

  val s = sum(list)
  val p = product(list)
  val l = length(list)

  println(s"sum=$s, product=$p, length=$l")
}
