package projet.IO

import com.typesafe.config.ConfigFactory
import org.scalatest.funsuite.AnyFunSuite
import projet.`enum`.Orientations
import projet.exception.DonneesIncorectesException
import projet.model.Lawn

class FileParserSpec extends AnyFunSuite {

  val fileParser = new FileParser(ConfigFactory.load())

  test("ParseLawn '5 5' should return a lawn with a size of 5 5") {
    val result = fileParser.parseLawn("5 5")
    assert(result === Lawn(5, 5))
  }

  test("ParseLawn '55' should throw an exception") {
    try {
      fileParser.parseLawn("55")
    } catch {
      case e: DonneesIncorectesException =>
        assert(
          e.getMessage === "Le nombre de paramètres de la taille de la pelouse est incorrect"
        )
      case _: Throwable => fail()
    }
  }

  test("ParseLawn '5' should throw an exception") {
    try {
      fileParser.parseLawn("5 5 5")
    } catch {
      case e: DonneesIncorectesException =>
        assert(
          e.getMessage === "Le nombre de paramètres de la taille de la pelouse est incorrect"
        )
      case _: Throwable => fail()
    }
  }

  test("ParseLawn '5 0' should throw an exception") {
    try {
      fileParser.parseLawn("5 0")
    } catch {
      case e: DonneesIncorectesException =>
        assert(
          e.getMessage === "La taille de la pelouse doit être supérieure à 0"
        )
      case _: Throwable => fail()
    }
  }

  test("parseActions 'AAAGD' should return a list of 5 actions") {
    val result = fileParser.parseActions("AAAGD")
    assert(result.length === 5)
  }

  test("parseActions 'BAAGD' should throw an exception") {
    try {
      fileParser.parseActions("BAAGD")
    } catch {
      case e: DonneesIncorectesException =>
        assert(
          e.getMessage === "Action inconnue: B"
        )
      case _: Throwable => fail()
    }
  }

  test("parseSpatialOrientation '1 2 N' should return a spatial orientation") {
    val result = fileParser.parseSpatialOrientation(Lawn(5, 5), "1 2 N")
    assert(result.position.x === 1)
    assert(result.position.y === 2)
    assert(result.orientation === Orientations.North)
  }

  test("parseSpatialOrientation '1 2' should throw an exception") {
    try {
      fileParser.parseSpatialOrientation(Lawn(5, 5), "1 2")
    } catch {
      case e: DonneesIncorectesException =>
        assert(
          e.getMessage === "Le nombre de paramètres de la position de la tondeuse est incorrect"
        )
      case _: Throwable => fail()
    }
  }

  test("parseSpatialOrientation '1 2 N 3' should throw an exception") {
    try {
      fileParser.parseSpatialOrientation(Lawn(5, 5), "1 2 N 3")
    } catch {
      case e: DonneesIncorectesException =>
        assert(
          e.getMessage === "Le nombre de paramètres de la position de la tondeuse est incorrect"
        )
      case _: Throwable => fail()
    }
  }

  test("parseSpatialOrientation '1 2 B' should throw an exception") {
    try {
      fileParser.parseSpatialOrientation(Lawn(5, 5), "1 2 B")
    } catch {
      case e: DonneesIncorectesException =>
        assert(
          e.getMessage === "Les données de position de la tondeuse sont incorrectes"
        )
      case _: Throwable => fail()
    }
  }

  test("parseSpatialOrientation '10 10' should throw an exception") {
    try {
      fileParser.parseSpatialOrientation(Lawn(5, 5), "10 10 N")
    } catch {
      case e: DonneesIncorectesException =>
        assert(
          e.getMessage === "La position de départ de la tondeuse est en dehors de la pelouse"
        )
      case _: Throwable => fail()
    }
  }

}
