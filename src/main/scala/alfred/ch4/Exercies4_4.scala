package alfred.ch4

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies4_4 extends App {

    def sequence1[A](a: List[Option[A]]): Option[List[A]] = a match {
        case Nil => Some(Nil)
        case h :: t => h.flatMap(hh => sequence1(t) map (hh :: _))
    }

    def map2[A,B,C](a: Option[A], b: Option[B])(f: (A,B) => C) : Option[C] = (a, b) match {
        case (Some(aa),Some(bb)) => Some(f(aa,bb))
        case  _ => None
    }

    def sequence2[A](a: List[Option[A]]): Option[List[A]] =
        a.foldRight[Option[List[A]]](Some(Nil))((x,y) => map2(x,y)(_ :: _))

    val s1 = List(Some(1),Some(2),Some(3))
    val s2 = List(Some(1),None,Some(3))

    println(sequence1(s1))
    println(sequence1(s2))
    println(sequence2(s1))
    println(sequence2(s2))
}
