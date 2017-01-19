package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.8 (p51)
object Exercies3_8 extends App {

  // List.apply 함수를 살펴보면 foldRigth에서 Nil과 Cons를 사용했을 때와 같음을 알 수 있다.
  val list1 = List(1,2,3)
  val list2 = List.foldRight(list1,Nil:List[Int])(Cons(_,_))
  println(list1 == list2)
}
