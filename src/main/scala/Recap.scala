import scala.annotation.tailrec

object Recap extends App {
  val aCondition: Boolean = false
  val aConditionedVal: Int = if (aCondition) 42 else 65
  val aCodeBlock: Unit = {
    if (aCondition) {
      print(46)
    }
  }

  /** Unit == void
    */
  val theUnit: Unit = println("hello Scala")
  val aDog: Animal = new Dog //Subtyping

  /** Recursion - Stack and tail
    */
  /** Method Notation
    */
  val aCrocodile = new Crocodile

  /** Anonymous classes
    */
  val aCarnivore = new Carnivore {
    override def eat(a: Animal): Unit = println("roar")
  }

  println(normalFactorial(5))

  /** Exceptions and try/catch/finally
    */
  val throwException = throw new RuntimeException
  val aPotentialFailure =
    try {
      throw new RuntimeException
    } catch {
      case e: Exception => "I caught an exception"
    } finally {
      println("some logs")
    }

  /** Functional Programming
    */
  val incrementer = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }
  val anonymousIncrementer: Int => Int = (x: Int) => x + 1

  /** map, filter, flatmap
    */
  val pairs = for {
    num <- List(1, 2, 3)
    char <- List('a', 'b', 'c')
  } yield s"${num}-${char}"

  /** Scala Collections - Seq, Arrays, Lists, Vectors, Maps, Tuples
    */
  val aMap = Map(
    "Mahesh" -> 123,
    "Sam" -> 234
  )
  aCrocodile.eat(aDog)
  aCrocodile eat aDog

  /** Functions
    */
  def aFunction(x: Int): Int = x + 1

  /** @param n  - Takes a number as a parameter to calculate the factorial
    * @return
    */
  def normalFactorial(n: Int): Int = {
    if (n <= 1) n
    else n * normalFactorial(n - 1)
  }

  /** @param n                    - Takes a number as a parameter to calculate the factorial
    * @param accumulator  -Stores the intermediate result of the factorial to avoid stack overflow
    * @return
    */
  @tailrec
  def factorial(n: Int, accumulator: Int): Int = {
    if (n <= 0) accumulator
    else factorial(n - 1, n * accumulator)
  }

  trait Carnivore {
    def eat(a: Animal): Unit
  }

  /** Generics
    */
  abstract class MyList[+A]

  /** Object oriented programming.
    */
  class Animal

  /** Packaging and Imports
    */

  class Dog extends Animal
  incrementer(1)

  class Crocodile extends Animal with Carnivore {
    override def eat(a: Animal): Unit = println("crunch")
  }
  List(1, 2, 3).map(anonymousIncrementer) //Higher order functions

  /** Case classes
    */
  case class Person(name: String, age: Int)

  /** Singleton and companions
    */
  object MyList

  /** Collection : Option, Try
    */
  val aOption = Some(2)

  /** Pattern Matching
    */

  val x = 2
  val order = x match {
    case 1 => "first"
    case 2 => "Second"
    case 3 => "Third"
    case _ => x
  }
  val bob = Person("Bob", 22)
  val greeting = bob match {
    case Person(n, _) => s"Hi, my name is ${n}"
  }
}
