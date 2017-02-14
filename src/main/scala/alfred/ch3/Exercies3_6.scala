package alfred.ch3

import scala.util.Try

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.6 (p47)
object Exercies3_6 extends App {

  println(Try{List.init(Nil)})
  println(List.init(List(1)))
  println(List.init(List(1,2,3)))


}
