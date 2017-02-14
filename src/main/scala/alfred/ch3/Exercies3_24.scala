package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.25 (p55)
object Exercies3_24 extends App {

  @annotation.tailrec
  def startWith[A](as1: List[A], as2: List[A]) : Boolean = (as1, as2) match {
    case (_,Nil) => true
    case (Cons(h1, t1), Cons(h2, t2)) if h1==h2 => startWith(t1, t2)
    case _ => false
  }

  @annotation.tailrec
  def hasSubsequence[A](sup: List[A], sub: List[A]) : Boolean = sup match {
    case Nil => sub == Nil
    case _ if startWith(sup, sub) => true
    case Cons(_, t) => hasSubsequence(t, sub)
  }

  val sup = List(1,2,3)
  println(hasSubsequence(sup,Nil))
  println(hasSubsequence(sup,List(1)))
  println(hasSubsequence(sup,List(1,2)))
  println(hasSubsequence(sup,List(1,2,3)))
  println(hasSubsequence(sup,List(2)))
  println(hasSubsequence(sup,List(2,3)))
  println(hasSubsequence(sup,List(3)))
}
