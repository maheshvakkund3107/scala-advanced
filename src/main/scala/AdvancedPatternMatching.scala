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

}
