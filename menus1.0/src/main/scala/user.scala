
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.model._
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import  net.ruippeixotog.scalascraper.scraper.HtmlExtractor


class User  {

  val browser = JsoupBrowser()
  val alppila = browser.get("https://www.sodexo.fi/ravintolat/ravintola-hdl-alppikortteli#nextweeklink")
  val myyrmäki = browser.get("https://www.sodexo.fi/ravintolat/metropolia-myyrmaki")
val myllypuro = browser.get("https://www.sodexo.fi/ravintolat/metropolia-myllypuro")
  val ikaalinen = browser.get("https://www.sodexo.fi/ravintolat/ikaalinen/ikaalisten-kasi-ja-taideteollisuusoppilaitos-ilona")

  var list :List[String] = List()
  var listAllergeenitAlppila :List[List[String]] = List()

  var listMyyrmäki :List[String] = List()
  var listAllergeenitMyyrmäki : List[List[String]] = List()

  var listMyllypuro :List[String]= List()
  var listAllergeeniMyllypuro : List[List[String]] = List()

  var listIkaalinen : List[String] = List()
  var listAllergeenitIkaalinen : List[List[String]] = List()


  for (i <- 0 to 19) {
      list =  (alppila >> element("#dialog-"+i) >> text(".header-title")) :: list


  }

  for (i <- 0 to 19) {
  val a = alppila >> elementList("#dialog-"+i +" .allergens")
    listAllergeenitAlppila =  a.map(_>> allText(".allergens")) :: listAllergeenitAlppila
  }


  for (i <- 0 to 19) {
  listMyyrmäki = (myyrmäki >> element("#dialog-"+i) >> text(".header-title")) :: listMyyrmäki
  }

  for (i <- 0 to 19) {
  val a = myyrmäki >> elementList("#dialog-"+i +" .allergens")
    listAllergeenitMyyrmäki =  a.map(_>> allText(".allergens")) :: listAllergeenitMyyrmäki
  }

  for (i <- 0 to 24) {
  listMyllypuro = (myllypuro >> element("#dialog-"+i) >> text(".header-title")) :: listMyllypuro
  }
for ( i <- 0 to 24) {
       val a =  myllypuro >> elementList("#dialog-"+i +" .allergens")
        listAllergeeniMyllypuro =  a.map(_>> allText(".allergens")) :: listAllergeeniMyllypuro
}

  for (i <- 0 to 14) {
  listIkaalinen = (ikaalinen >> element("#dialog-"+i) >> text(".header-title")) :: listIkaalinen
  }

  for (i <- 0 to 14) {
   val a =  ikaalinen >> elementList("#dialog-"+i +" .allergens")
        listAllergeenitIkaalinen =  a.map(_>> allText(".allergens")) :: listAllergeenitIkaalinen
  }
def suosikkiruoka(ruoka: String,  päivä: String, ravintola: String) : List[String] = {
    var kaikki :List[String] = List()
  if ( ravintola == "Alppila") {
      val x = päivä match {
        case "maanantai" => (list.drop(16).take(4),listAllergeenitAlppila.drop(16).take(4))
        case "tiistai" => (list.drop(12).take(4), listAllergeenitAlppila.drop(12).take(4))
        case "keskiviikko" => (list.drop(8).take(4), listAllergeenitAlppila.drop(8).take(4))
        case "torstai" => (list.drop(4).take(4), listAllergeenitAlppila.drop(4).take(4))
        case "perjantai" => (list.take(4), listAllergeenitAlppila.take(4))
      }

    for (i <- 0 until 4) {

        if (x._2(i).contains(ruoka)) {
        kaikki = x._1(i)::kaikki

        }
    }
    if (kaikki.isEmpty) {
     println("kirjoittamaasi ruokaa ei löytynyt")

    }
    return kaikki
  } else if (ravintola == "Myyrmäki"){
 val x = päivä match {
        case "maanantai" => (listMyyrmäki.drop(16).take(4),listAllergeenitMyyrmäki.drop(16).take(4))
        case "tiistai" => (listMyyrmäki.drop(12).take(4), listAllergeenitMyyrmäki.drop(12).take(4))
        case "keskiviikko" => (listMyyrmäki.drop(8).take(4), listAllergeenitMyyrmäki.drop(8).take(4))
        case "torstai" => (listMyyrmäki.drop(4).take(4), listAllergeenitMyyrmäki.drop(4).take(4))
        case "perjantai" => (listMyyrmäki.take(4), listAllergeenitMyyrmäki.take(4))
      }

    for (i <- 0 until x._2.length) {

        if (x._2(i).contains(ruoka)) {
        kaikki = x._1(i)::kaikki

        }
    }
    if (kaikki.isEmpty) {
     println("kirjoittamaasi ruokaa ei löytynyt")

    }
  return kaikki
  } else if (ravintola == "Myllypuro") {
  val x = päivä match {
        case "maanantai" => (listMyllypuro.drop(20).take(5), listAllergeeniMyllypuro.drop(20).take(5))
        case "tiistai" => (listMyllypuro.drop(15).take(5),  listAllergeeniMyllypuro.drop(15).take(5))
        case "keskiviikko" => (listMyllypuro.drop(10).take(5), listAllergeeniMyllypuro.drop(10).take(5))
        case "torstai" => (listMyllypuro.drop(5).take(5), listAllergeeniMyllypuro.drop(5).take(5))
        case "perjantai" => (listMyllypuro.take(5),  listAllergeeniMyllypuro.take(5))
      }

    for (i <- 0 until x._2.length) {

        if (x._2(i).contains(ruoka)) {
        kaikki = x._1(i)::kaikki

        }
  }
     if (kaikki.isEmpty) {
     println("kirjoittamaasi ruokaa ei löytynyt")

    }
  return kaikki
  }else if (ravintola == "Ikaalinen")  {
    val x = päivä match {
        case "maanantai" => (listIkaalinen.drop(11).take(3), listAllergeenitIkaalinen.drop(11).take(3))
        case "tiistai" => (listIkaalinen.drop(8).take(3),  listAllergeenitIkaalinen.drop(8).take(3))
        case "keskiviikko" => (listIkaalinen.drop(5).take(3), listAllergeenitIkaalinen.drop(5).take(3))
        case "torstai" => (listIkaalinen.drop(2).take(3), listAllergeenitIkaalinen.drop(2).take(3))
        case "perjantai" => (listIkaalinen.take(3),  listAllergeenitIkaalinen.take(3))
      }

    for (i <- 0 until x._2.length) {

        if (x._2(i).contains(ruoka)) {
        kaikki = x._1(i)::kaikki

        }
  }
     if (kaikki.isEmpty) {
     println("kirjoittamaasi ruokaa ei löytynyt")

    }
  return kaikki
  } else {
  return kaikki
  }

  }




def check(allergeenit: List[String], päivä: String, ravintola: String) : List[String] =
  {
var i = 0
    var j = 0
    var kaikki :List[String] = List()
    if (ravintola == "Alppila") {
    val x = päivä match {
        case "maanantai" => (list.drop(16).take(4),listAllergeenitAlppila.drop(16).take(4))
        case "tiistai" => (list.drop(12).take(4), listAllergeenitAlppila.drop(12).take(4))
        case "keskiviikko" => (list.drop(8).take(4), listAllergeenitAlppila.drop(8).take(4))
        case "torstai" => (list.drop(4).take(4), listAllergeenitAlppila.drop(4).take(4))
        case "perjantai" => (list.take(4), listAllergeenitAlppila.take(4))
    }


  for (i <- 0 until x._2.length) {
    for ( j <- 0 until allergeenit.length) {


     var a =  allergeenit(j).drop(1).dropRight(1).split(',')
     var t = true

      for ( k <- 0 until a.length) {
       if (x._2(i).contains(a(k))) {


         t = false
       }
      }
      if(t) {
       kaikki = x._1(i) :: kaikki
      }

    }


  }


       if(allergeenit.isEmpty) {
      return x._1
      }
return kaikki

} else if (ravintola == "Myllypuro") {
    val x = päivä match {
        case "maanantai" => (listMyllypuro.drop(20).take(5),listAllergeeniMyllypuro.drop(20).take(5))
        case "tiistai" => (listMyllypuro.drop(15).take(5), listAllergeeniMyllypuro.drop(15).take(5))
        case "keskiviikko" => (listMyllypuro.drop(10).take(5), listAllergeeniMyllypuro.drop(10).take(5))
        case "torstai" => (listMyllypuro.drop(5).take(5), listAllergeeniMyllypuro.drop(5).take(5))
        case "perjantai" => (listMyllypuro.take(5), listAllergeeniMyllypuro.take(5))
    }
  for (i <- 0 until x._2.length) {
    for ( j <- 0 until allergeenit.length) {


     var a =  allergeenit(j).drop(1).dropRight(1).split(',')
     var t = true

      for ( k <- 0 until a.length) {
       if (x._2(i).contains(a(k))) {


         t = false
       }
      }
      if(t) {
       kaikki = x._1(i) :: kaikki
      }

    }


  }

       if(allergeenit.isEmpty) {
      return x._1
      }
return kaikki

  } else if (ravintola == "Ikaalinen") {
    val x = päivä match {
        case "maanantai" => (listIkaalinen.drop(11).take(3),listAllergeenitIkaalinen.drop(11).take(3))
        case "tiistai" => (listIkaalinen.drop(8).take(3), listAllergeenitIkaalinen.drop(8).take(3))
        case "keskiviikko" => (listIkaalinen.drop(5).take(3), listAllergeenitIkaalinen.drop(5).take(3))
        case "torstai" => (listIkaalinen.drop(2).take(3), listAllergeenitIkaalinen.drop(2).take(3))
        case "perjantai" => (listIkaalinen.take(3), listAllergeenitIkaalinen.take(3))
    }
  for (i <- 0 until x._2.length) {
    for ( j <- 0 until allergeenit.length) {


     var a =  allergeenit(j).drop(1).dropRight(1).split(',')
     var t = true

      for ( k <- 0 until a.length) {
       if (x._2(i).contains(a(k))) {


         t = false
       }
      }
      if(t) {
       kaikki = x._1(i) :: kaikki
      }

    }


  }

       if(allergeenit.isEmpty) {
      return x._1
      }
return kaikki


} else if (ravintola == "Myyrmäki") {
    val x = päivä match {
        case "maanantai" => (listMyyrmäki.drop(16).take(4),listAllergeenitMyyrmäki.drop(16).take(4))
        case "tiistai" => (listMyyrmäki.drop(12).take(4), listAllergeenitMyyrmäki.drop(12).take(4))
        case "keskiviikko" => (listMyyrmäki.drop(8).take(4), listAllergeenitMyyrmäki.drop(8).take(4))
        case "torstai" => (listMyyrmäki.drop(4).take(4), listAllergeenitMyyrmäki.drop(4).take(4))
        case "perjantai" => (listMyyrmäki.take(4), listAllergeenitMyyrmäki.take(4))
    }
 for (i <- 0 until x._2.length) {
    for ( j <- 0 until allergeenit.length) {


     var a =  allergeenit(j).drop(1).dropRight(1).split(',')
     var t = true

      for ( k <- 0 until a.length) {
       if (x._2(i).contains(a(k))) {


         t = false
       }
      }
      if(t) {
       kaikki = x._1(i) :: kaikki
      }

    }


  }

       if(allergeenit.isEmpty) {
      return x._1
      }
return kaikki

    } else {
      return kaikki
  }
  }
}