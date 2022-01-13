
import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Global.readline

import scala.io.StdIn._
import scala.io.StdIn.readLine
object Run extends App {

  val joku =  new User
  println("Kirjoita a jos haluat etsiä lempiruokaasi ja b jos taas haluat etsiä ruokia allergeenien perusteella")
  val a  = readLine()
  if ( a == "a"){
   println("Kirjoita lempiruoka tai lempiainesosa isolla alkukirjaimella ja paina enter")
val ruoka = readLine()
  println("Kirjoita viikonpäivä ja paina enter. Ravintolat ovat kiinni la ja su")
  val päivä = readLine()
  println("Kirjoita paikka, jossa haluat syödä ja paina enter. Vaihtoehdot ovat Alppila, Myyrmäki, Myllypuro ja Ikaalinen. Ravintolana toimii sodexo")
  val ravintola = readLine()
  println(joku.suosikkiruoka(ruoka,päivä,ravintola).mkString("\n"))
  } else if ( a == "b"){
  println("Kirjoita allergeenisi listana, sulkujen sisään ja pilkulla erotettuina. Esim (Maito, Kala,..) ja paina enter. Muista kirjoittaa allergeenit isolla kirjaimella!")
    var allergeenit :List[String] = List()
     allergeenit = readLine() :: allergeenit

    println("Kirjoita viikonpäivä ja paina enter")
    val päivä = readLine()
    println("Kirjoita ravintola, jossa haluat syödä ja paina enter")
    val ravintola = readLine()
    println(joku.check(allergeenit, päivä, ravintola).mkString("\n"))
  } else {
  println("error")

  }


}
