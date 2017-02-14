package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.23 (p54)
object Exercies3_23 extends App {

   def zipWith[A, B, C](as1 : List[A], as2: List[B])( f: (A, B) => C ) : List[C] = (as1, as2) match {
     case (Cons(h1 , t1), Cons(h2, t2)) => Cons[C](f(h1, h2), zipWith[A, B, C](t1, t2)(f))
     case _ => Nil
   }

   val strList = List("abc", "ijk", "lmn")
   val idxList = List(0, 1, 2)
   val chrList = zipWith(strList,idxList)( (s,i) => s.charAt(i))
   println(chrList) // List('a','j','n')
}
