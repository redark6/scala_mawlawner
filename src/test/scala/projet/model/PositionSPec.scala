package projet.model

import org.scalatest.funsuite.AnyFunSuite

class PositionSPec extends AnyFunSuite {
  val position: Position = Position(0, 0)
  test("The position x should be 0") {
    assert(position.x === 0)
  }

  test("The position y should be 0") {
    assert(position.y === 0)
  }

  test("The position moveOf(1,1) should be (1,1)") {
    assert(position.moveOf(1, 1) === Position(1, 1))
  }

  test("The position moveOf(1,0) should be (1,0)") {
    assert(position.moveOf(1, 0) === Position(1, 0))
  }

  test("The position moveOf(0,1) should be (0,1)") {
    assert(position.moveOf(0, 1) === Position(0, 1))
  }

}
