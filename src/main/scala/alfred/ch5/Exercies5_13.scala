package alfred.ch5

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies5_13 extends App {

  import Stream._

  val s = unfold(0)( s=> if(s >10 ) None else Some(s,s+1))
  println(s.toList)
  println(s.takeViaUnfold(3).mapViaUnfold(_ * 2).toList)
  println(s.takeViaUnfold(12).mapViaUnfold(_ * 2).toList)
  println(s.takeWhileViaUnfold(_ < 6).toList)
  println(s.takeWhileViaUnfold(_ < 12).toList)

  val s1 = Stream(1,2,3)
  val s2 = Stream("A","B")
  println(s1.zipAll(s2).toList)
  println(s2.zipAll(s1).toList)

}
