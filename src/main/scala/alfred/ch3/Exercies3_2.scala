package alfred.ch3

import scala.util.Try

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.2 (p45)
object Exercies3_2 extends App {

  val emptyList : List[Int] = List()
  val list = List(1,2,3)

  println(Try{List.tail(emptyList)})
  println(List.tail(list))
}
