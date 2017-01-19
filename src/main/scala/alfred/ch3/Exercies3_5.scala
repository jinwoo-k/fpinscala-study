package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.5 (p46)
object Exercies3_5 extends App {

  val emptyList : List[Int] = List()
  val list = List(1,2,3)

  println(List.dropWhile(emptyList, (a:Int) => a < 2 ))
  println(List.dropWhile(list, (a:Int) => a < 2 ))
  println(List.dropWhile(list, (a:Int) => a < 4 ))

  // p48  파라메터를 두 그룹으로 나누어 추론 가능하도록
  println(List.dropWhile2(emptyList)(_ < 2))
  println(List.dropWhile2(list)(_ < 2))
  println(List.dropWhile2(list)(_ < 4))
}
