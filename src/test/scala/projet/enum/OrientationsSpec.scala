package projet.`enum`

import org.scalatest.funsuite.AnyFunSuite

class OrientationsSpec extends AnyFunSuite {

  test("The orientation North should be 'N'") {
    assert(Orientations.North.name === "N")
  }

  test("The orientation East should be 'E'") {
    assert(Orientations.East.name === "E")
  }

  test("The orientation South should be 'S'") {
    assert(Orientations.South.name === "S")
  }

  test("The orientation West should be 'W'") {
    assert(Orientations.West.name === "W")
  }

}
