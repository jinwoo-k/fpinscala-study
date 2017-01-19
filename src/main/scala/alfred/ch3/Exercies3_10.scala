package alfred.ch3


/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.10 (p51)
object Exercies3_10 extends App {

  val list = (1 to 10000).foldRight(Nil:List[Int])(Cons(_,_))

  val sum1 = List.foldLeft(list,0)( (s, a) => s + a )
  println(s"sum1=$sum1")
  Thread.sleep(500) // 에러 출력과 위의 결과 출력이 섞이지 않도록.
  val sum2 = List.foldRight(list,0)( (a, s) => s + a )
  println(s"sum2=$sum2")
}
