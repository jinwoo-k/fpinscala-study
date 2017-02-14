package alfred.ch2

/**
 * Created by alfredkim on 2017. 1. 16..
 */
object Exercies2_2 extends App {

  /*  연습문제 2-2 (p31) */
  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    @annotation.tailrec
    def checkOrder(n: Int): Boolean =
      if (n >= as.length - 1) true
      else if (ordered(as(n), as(n + 1))) checkOrder(n + 1)
      else false
    checkOrder(0)
  }

  val as = (1 to 10).toArray[Int]
  println(isSorted(as,(a:Int,b:Int)=> a < b ))
  println(isSorted(as,(a:Int,b:Int)=> a <= b ))

}
