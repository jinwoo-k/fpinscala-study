package alfred.ch4

/**
 * Created by alfredkim on 2017. 2. 3..
 */
object Exercies4_8 extends App {

  case class Person(name: Name, age: Age)
  sealed class Name(val value : String)
  sealed class Age(val value : Int)

  def mkName(name: String): Either[String, Name] =
    if(name == "" || name == null) Left("Name is empty")
    else Right(new Name(name))

  def mkAge(age : Int): Either[String, Age] =
    if(age < 0 ) Left("Age is out of range.")
    else Right(new Age(age))

   def mkPerson(name : String, age: Int): Either[String, Person] = mkName(name).map2(mkAge(age))( (name,age) => Person(name, age))

  
}

