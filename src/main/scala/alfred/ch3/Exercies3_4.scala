package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.4 (p46)
object Exercies3_4 extends App {

  val emptyList : List[Int] = List()
  val list = List(1,2,3)

  // drop 시 n이 양수가 아니면 원래 리스트를 반환
  println(List.drop(emptyList,0))
  println(List.drop(emptyList,2))
  println(List.drop(list,0))
  println(List.drop(list,2))
  println(List.drop(list,4))
}
