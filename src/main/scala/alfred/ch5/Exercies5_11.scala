package alfred.ch5

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies5_11 extends App {

  import Stream._

  val s = unfold(0)( s => if(s>10) None else Some(s*2+1 ,s+1))
  println(s.toList)

}
