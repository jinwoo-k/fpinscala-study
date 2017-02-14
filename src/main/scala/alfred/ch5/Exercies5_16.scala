package alfred.ch5

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies5_16 extends App {

  println(Stream(1,2,3).scanRight(0)(_ + _))
}
