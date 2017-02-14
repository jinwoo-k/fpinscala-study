package alfred.ch5

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies5_15 extends App {
  println(Stream(1,2,3).tails)
  println(Stream(1,2,3).hasSubsequence(Stream(2,3)))
}
