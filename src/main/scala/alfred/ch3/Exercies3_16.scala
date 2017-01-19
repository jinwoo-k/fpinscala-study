package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.16 (p53)
object Exercies3_16 extends App {

  def add1(l: List[Int]) : List[Int] = List.foldRight[Int,List[Int]](l,Nil)( (a,g) => Cons(a + 1,g))

  println(add1(List(1,2,3)))

}
