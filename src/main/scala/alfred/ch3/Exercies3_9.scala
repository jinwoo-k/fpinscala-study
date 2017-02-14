package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.9 (p51)
object Exercies3_9 extends App {
  def length[A](as: List[A]) : Int = List.foldRight(as,0)((_,len) => len+1 )

  val list = List(1,2,3)
  println(length(list))
}
