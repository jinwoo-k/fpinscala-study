package alfred.ch5

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies5_7 extends App {

    val s  = Stream(1,2,3)
    println("-- map ---")
    println(s.map(_ * 2).toList)
    println("-- filter --")
    println(s.filter(_ % 2 !=0).toList)
    println("-- append --")
    val s1 = s.append(Stream(4,5))
    println(s1.toList)
    println("-- flatMap --")
    println(s1.flatMap(a => Stream( a * 2)).toList)
    val s2 = Stream(Stream(1),Empty,Stream(2,3))
    println(s2.flatMap(identity).toList)

}
