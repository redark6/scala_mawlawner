package projet.`enum`

import org.scalatest.funsuite.AnyFunSuite

class MovementsSpec extends AnyFunSuite {

  test("The forward movement name should be 'A'") {
    assert(Movements.Forward.name === "A")
  }

  test("The forward shift should be 1") {
    assert(Movements.Forward.shift === 1)
  }

  test("Parsing 'A' should return the forward movement") {
    assert(Movements.nameToMovement('A') === Some(Movements.Forward))
  }

  test("Parsing any other character should return None") {
    val otherChars = ('B' to 'Z') ++ ('a' to 'z') ++ ('0' to '9')
    val resultParse = otherChars.map(Movements.nameToMovement)
    assert(resultParse.forall(_ === None))
  }

}
