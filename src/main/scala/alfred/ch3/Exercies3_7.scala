package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.7 (p51)
object Exercies3_7 extends App {


  def foldRight1[A,B](as: List[A], z: B)(f: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight1(xs, z)(f))
    }

  def foldRight2[A,B](as: List[A], z: B)(f: (A, => B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight2(xs, z)(f))
    }

  def product1(ns: List[Double]) =
    foldRight1(ns, 1.0)( (a,b) => {
      println(s"call foldRight1 with a=$a")
      if(a==0) 0
      else a * b
    })

  def product2(ns: List[Double]) =
    foldRight2(ns, 1.0)( (a,b) => {
      println(s"call foldRight2 with a=$a")
      if(a == 0.0) 0.0
      else a * b
    })

  val list : List[Double] = List(1.0, 0.0, 2.0, 3.0)
  product1(list)
  product2(list)
}
