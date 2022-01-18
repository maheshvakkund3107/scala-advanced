object AdvancedPatternMatching extends App {
  val numbers = List(1)
  val description: Unit = numbers match {
    case head :: Nil => println(s"the only element is $head")
    case _           =>
  }

  /** -Constants
    * -WildCards
    * -Case Classes
    * -Tuples
    * -Some special magic like above
    */

  class Person(val name: String, val age: Int)

  object Person {
    def unapply(person: Person): Option[(String, Int)] =
      if (person.age < 21) None
      else
        Some(
          (person.name, person.age)
        )

    def unapply(age: Int): Option[String] = Some(
      if (age < 21) "minor" else "major"
    )
  }
  val bob = new Person("Bob", 22)
  val greeting: Any = bob match {
    case Person(n, a) => s"Hi , my name is $n and I am $a"
  }
  println(greeting)

  val legalStatus = bob.age match {
    case Person(status) => s"My Legal status is $status"
  }
  println(legalStatus)

  /** Exercise
    */

  val n: Int = 12
  var mathProperty = n match {
    case x if x < 10     => "Single Digit"
    case x if x % 2 == 0 => "an even number"
    case _               => "no property"
  }

  object even {
    def unapply(arg: Int): Option[Boolean] = {
      if (arg % 2 == 0) Some(true)
      else None
    }
  }
  object singleDigit {
    def unapply(arg: Int): Option[Boolean] = {
      if (arg < 10) Some(true)
      else None
    }
  }
  mathProperty = n match {
    case singleDigit(_) => "Single Digit"
    case even(_)        => "an even number"
    case _              => "no property"
  }
  println(mathProperty)

  /** Infix Patterns.
    */

  case class Or[A, B](a: A, b: B)
  val either = Or(2, "two")
  val humanDesc: String = either match {
    case number Or string => s"$number is written as $string"
  }
  println(humanDesc)

  /** Decomposing Sequences
    */

  val vararg = numbers match {
    case List(1, _*) => "starting with 1"
  }

  abstract class MyList[+A] {
    def head: A = ???
    def tail: MyList[A] = ???
  }

  case object Empty extends MyList[Nothing]
  case class Cons[+A](override val head: A, override val tail: MyList[A])
      extends MyList[A]
  object MyList {
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] = {
      if (list == Empty) Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
    }
  }
  val myList: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val decomposed = myList match {
    case MyList(1, 2, _*) => "Starting with 1,2"
    case _                => "Something else"
  }
  println(decomposed)

  /** Custom return types for unapply
    * isEmpty: Boolean, get :Something
    */

  abstract class Wrapper[T] {
    def isEmpty: Boolean
    def get: T
  }

  object PersonWrapper {
    def unapply(person: Person): Wrapper[String] = new Wrapper[String] {
      override def isEmpty: Boolean = false
      override def get: String = person.name
    }
  }

  println(bob match {
    case PersonWrapper(n) => s"This persons name is $n"
    case _                => "An Alien"
  })
}
