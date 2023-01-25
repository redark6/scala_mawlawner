package projet.model

import org.scalatest.funsuite.AnyFunSuite

class LawnSpec extends AnyFunSuite {
  val lawn: Lawn = Lawn(5, 5)

  test("The lawn width should be 5") {
    assert(lawn.width === 5)
  }

  test("The lawn height should be 5") {
    assert(lawn.height === 5)
  }

  test("The lawn height border should be 4") {
    assert(lawn.heightBorder === 4)
  }

  test("The lawn width border should be 4") {
    assert(lawn.widthBorder === 4)
  }

  test("a position in (2,2) is inside the lawn") {
    assert(lawn.isInsideLawn(Position(2, 2)) === true)
  }

  test("a position in (10,10) is outside the lawn") {
    assert(lawn.isInsideLawn(Position(10, 10)) === false)
  }

  test("a position in (5,5) is outside the lawn") {
    assert(lawn.isInsideLawn(Position(10, 10)) === false)
  }
}
