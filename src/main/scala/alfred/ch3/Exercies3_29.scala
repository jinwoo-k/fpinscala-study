package alfred.ch3

/**
 * Created by alfredkim on 2017. 1. 18..
 */

// 연습문제 3.28 (p57)
object Exercies3_29 extends App {

  val left1 = Leaf(1)
  val right1 = Leaf(2)
  val branch1 = Branch(left1,right1)
  val left2 = Leaf(3)
  val right2 = Leaf(4)
  val branch2 = Branch(left2,right2)
  val root = Branch(branch1,branch2)

  println(Tree.size(root))
  println(Tree.depth(root))
  println(Tree.maximum(root))
  println(Tree.map(root)(_ * 10))
  println()
  println(Tree.size1(root))
  println(Tree.depth1(root))
  println(Tree.maximum1(root))
  println(Tree.map1(root)(_ * 10))

  /* List의 foldLeft,foldRight 와 Tree의 fold 의 유사성
    1. 둘다 생성자 패턴 매치를 통해 해당생성자의 인수를 얻어 주어진 함수를 적용하여 리턴한다
    2. List의 Cons 나 Tree.Branch는 재귀적 형태이므로 fold 구현에서도 재귀를 사용한다
  */
}
