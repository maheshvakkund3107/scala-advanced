package advancedfunctionalprogramming

object PartialFunctions extends App {
  val aFunctions: Int => Int = (x: Int) => x + 1
  val aFussyFunction: Int => Int = (x: Int) =>
    if (x == 1) 42
    else if (x == 2) 56
    else if (x == 5) 999
    else throw new FunctionNotApplicableException

  class FunctionNotApplicableException extends RuntimeException

  /**  val aNicerFussyFunction: Int => Int = (x: Int) =>
    *    x match {
    *      case 1 => 42
    *      case 2 => 56
    *      case 5 => 999
    *    }
    */

  val aPartialFUnction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  }
  println(aPartialFUnction(2))

  /** Partial Function Utilities
    */
  /** aPartialFUnction(200) will crash our program
    * Checks whether this element exists in the case or not without crashing our program
    */
  println(aPartialFUnction.isDefinedAt(67))

  /** Lift
    * Int =>Option[Int]
    */
  val lifted = aPartialFUnction.lift
  println(lifted(2))
  println(lifted(98))

  val pfChain = aPartialFUnction.orElse[Int, Int] { case 45 =>
    67
  }
  println(pfChain(2))
  println(pfChain(45))

  /** Partial function extend Normal function.
    */

  val aNicerFussyFunction: Int => Int = {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  }
  println(aNicerFussyFunction(2))

  /** HOF accept partial functions as well
    */
  val aMappedList = List(1, 2, 3).map {
    case 1 => 42
    case 2 => 78
    case 3 => 1000
  }
  println(aMappedList)

  /** Note: PF can only have ONE parameter type.
    */

  /** Exercise
    * 1 - Construct a PF instance yourself (anonymous class)
    * 2- dumb chat bot as a PF
    */

  /** If we dint use the syntactic sugar this would have been the approach
    */
  val aManualFussyFunction = new PartialFunction[Int, Int] {
    override def apply(x: Int): Int = x match {
      case 1 => 42
      case 2 => 65
      case 5 => 999
    }
    override def isDefinedAt(x: Int): Boolean = x == 1 || x == 2 || x == 5
  }

  val chatBot: PartialFunction[String, String] = {
    case "hello"   => "Hi Name is HAL9000"
    case "goodBye" => "Bye"
  }
  scala.io.Source.stdin
    .getLines()
    .foreach(line => println("chat bot says:" + chatBot(line)))

}
