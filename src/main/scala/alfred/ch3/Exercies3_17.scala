package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.17 (p53)
object Exercies3_17 extends App {

  def toStringList(l: List[Double]) : List[String] = List.foldRight[Double,List[String]](l,Nil)( (d, s) => Cons(d.toString, s))

  val dblList = List[Double](0.00, 1.00, 2.00)
  val strList = toStringList(dblList)
  println(strList)
}
