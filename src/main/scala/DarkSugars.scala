import java.awt.Composite
import scala.util.Try

object DarkSugars extends App {

  /** Syntax Sugars 1 : Methods with single param
    */
  def singleArgMethods(arg: Int): String = s"$arg little ducks"

  val description = singleArgMethods {

    /** Write some complex code
      */
    42
  }

  val aTryInstance = Try { //Java try
    throw new RuntimeException
  }

  List(1, 2, 3).map { x =>
    x + 1
  }

  /** Syntax Sugars 2: Single Abstract Methods.
    */

  trait Action {
    def act(x: Int): Int
  }

  /** val anInstance: Action = new Action {
    * override def act(x: Int): Int = { x + 1 }
    * }
    */

  val anInstance1: Action = (x: Int) => x + 1

  /** val aThread = new Thread(new Runnable {
    * override def run(): Unit = print("Hello Scala")
    * })
    */

  val aSweetThread = new Thread(() => print("Hello Scala"))

  abstract class AnAbstractType {
    def implemented: Int = 23

    def f(a: Int): Unit
  }

  val anAbstractInstance: AnAbstractType = (a: Int) => {
    println("sweet")
  }

  /** Syntax Sugars 3: The :: and #: methods are special.
    */
  val prependList = 2 :: List(4, 5)

  /** Last character decides associativity of the method
    *
    *  1 :: 2 :: 3 :: 4 :: List(4, 5)
    *  List(4, 5).::(3).::(2).::(1) //equivalent
    */

  class MyStream[T] {
    def -->:(value: T): MyStream[T] = this //Actual Implementation here
  }
  val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]

  /** Syntax Sugars 4: MultiWord method naming
    */
  class TeenBoy(name: String) {
    def `and then said`(gossip: String): Unit = println(s"$name said $gossip")
  }
  val lilly = new TeenBoy("Lilly")
  lilly `and then said` "Scala is so sweet"

  /** Syntax Sugar 5: Infix Types.
    */
  class Composite[A, B]
  val composite: Composite[Int, String] = new Composite()
  val composite1: Int Composite String = new Composite()

  class -->[A, B]
  val towards: Int --> String = new -->()

  /** Syntax Sugar 6: update() is very special , much like apply(
    */
  val anArray = Array(1, 2, 3, 4)
  anArray(2) = 7 //Rewritten to anArray.update(2,7)

  /** Used in mutable collection remember apply() and update()
    */

  /** Syntax Sugar 7 : Setters for mutable containers.
    */
  class Mutable {
    private var internalMember: Int = 0 //private for oo encapsulation
    def member: Int = internalMember //getter
    def member_=(value: Int): Unit = //setter
      internalMember = value

  }
  val aMutableContainer = new Mutable

  /** Rewritten as aMutableContainer.member_=(42)
    */
  aMutableContainer.member = 42
}
