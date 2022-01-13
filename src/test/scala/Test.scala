
import org.scalatest.funsuite.AnyFunSuite

import collection.mutable.Buffer
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import scala.Console.in
 class Test extends AnyFunSuite () {
 {
    val buffer = Buffer[String]()

    assume(buffer.isEmpty)

 buffer += "Liha"
 assert("Liha" === buffer.last)

 buffer += "Kala"
 assertResult("Kala") {
 buffer.last
 }
}


  {
    val buffer = Buffer[String]()

    buffer += "Liha"
   assert(buffer.contains("Liha"))


  }


{
  val buffer = Buffer[String]()

  buffer += "Maito"

  assert(!buffer.contains("Vehnä"))

}

 }
