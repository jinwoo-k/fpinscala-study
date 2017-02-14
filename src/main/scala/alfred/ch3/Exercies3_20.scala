package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.20 (p53)
object Exercies3_20 extends App {

  def flatMap[A,B](l: List[A])(f: A => List[B]): List[B] =  List.concat(List.map(l)(f))

  val list = flatMap(List(1,2,3))(  a => List(a,a) )
  println(list)
}
