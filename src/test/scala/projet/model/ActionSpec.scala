package projet.model

import org.scalatest.funsuite.AnyFunSuite
import projet.`enum`.{Movements, Rotations}

class ActionSpec extends AnyFunSuite {

  val forward: Movement = Movement(Movements.Forward)
  val right: Rotation = Rotation(Rotations.Right)
  val left: Rotation = Rotation(Rotations.Left)

  test("The forward action name should be 'A'") {
    assert(forward.name === "A")
  }

  test("The right action name should be 'D'") {
    assert(right.name === "D")
  }

  test("The left action name should be 'G'") {
    assert(left.name === "G")
  }

}
