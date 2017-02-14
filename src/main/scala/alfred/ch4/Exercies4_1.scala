package alfred.ch4

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies4_1 extends App {
    val n : Option[Int] = None
    val a : Option[Int] = Some(1)


    // p66 참고 함수설명 참고

    // map function
    // Some -> Some 가능
    // Some -> None 불가
    // None -> Some 불가
    // None -> None 가능
    println(a.map(_ * 1.0)) // Some(a) -> Some(b)
    println(n.map(_ * 1.0)) // None -> None

    // flatMap function
    // Some -> Some 가능
    // Some -> None 가능(책의 표현대로 하면 실패할 수 있는 경우)
    // None -> Some 불가
    // None -> None 가능

    // 역수로 변환하기(0 일 때 실패할수 있는 함수)
    def inverse(a: Option[Int]) : Option[Double] = {
        a.flatMap( a => if (a == 0) None else Some(1.toDouble/a)  )
    }
    println(inverse( Some(100))) // Some -> Some
    println(inverse( Some(0))) // Some -> None

    // 필터는 결국 성공한 케이스에 자신을 실패한 케이스에 None 돌려주는 flatMap이라고 할 수 있음
    def even(i:Int ) = i%2 == 0
    println(Some(2).flatMap( a => if(even(a)) Some(a) else None))
    println(Some(2).filter(even))
    println(Some(1).flatMap( a => if(even(a)) Some(a) else None))
    println(Some(1).filter(even))
}
