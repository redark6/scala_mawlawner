package projet.`enum`

import org.scalatest.funsuite.AnyFunSuite

class RotationsSpec extends AnyFunSuite {

  test("The rotation Right should be 'D'") {
    assert(Rotations.Right.name === "D")
  }

  test("The rotation Left should be 'G'") {
    assert(Rotations.Left.name === "G")
  }

  test("The rotation Right should be -90") {
    assert(Rotations.Right.degAngle === -90)
  }

  test("The rotation Left should be 90") {
    assert(Rotations.Left.degAngle === 90)
  }

  test("Parsing 'D' should return Right") {
    assert(Rotations.nameToRotation('D') === Some(Rotations.Right))
  }

  test("Parsing 'G' should return Left") {
    assert(Rotations.nameToRotation('G') === Some(Rotations.Left))
  }

  test("Parsing any other char than 'D' and 'G' should return None") {
    val otherChars = ('A' to 'C') ++ ('E' to 'F') ++ ('H' to 'Z') ++ ('a' to 'z') ++ ('0' to '9')
    val resultParse = otherChars.map(Rotations.nameToRotation)
    assert(resultParse.forall(_ === None))
  }

}
