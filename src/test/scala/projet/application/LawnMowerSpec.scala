package projet.application

import org.scalatest.funsuite.AnyFunSuite
import projet.`enum`.{Movements, Orientations, Rotations}
import projet.model.{Lawn, Movement, Position, Rotation, SpatialOrientation}

class LawnMowerSpec extends AnyFunSuite {

  val lawnMower = new LawnMower(
    SpatialOrientation(Position(2, 2), Orientations.North),
    Lawn(5, 5)
  )

  test("Rotate left from North to West") {
    val result = lawnMower.rotate(
      Rotation(Rotations.Left),
      SpatialOrientation(Position(1, 1), Orientations.North)
    )
    assert(result.orientation === Orientations.West)
  }

  test("Rotate right from North to East") {
    val result = lawnMower.rotate(
      Rotation(Rotations.Right),
      SpatialOrientation(Position(1, 1), Orientations.North)
    )
    assert(result.orientation === Orientations.East)
  }

  test("Rotate left from East to North") {
    val result = lawnMower.rotate(
      Rotation(Rotations.Left),
      SpatialOrientation(Position(1, 1), Orientations.East)
    )
    assert(result.orientation === Orientations.North)
  }

  test("Rotate right from East to South") {
    val result = lawnMower.rotate(
      Rotation(Rotations.Right),
      SpatialOrientation(Position(1, 1), Orientations.East)
    )
    assert(result.orientation === Orientations.South)
  }

  test("Rotate left from South to East") {
    val result = lawnMower.rotate(
      Rotation(Rotations.Left),
      SpatialOrientation(Position(1, 1), Orientations.South)
    )
    assert(result.orientation === Orientations.East)
  }

  test("Rotate right from South to West") {
    val result = lawnMower.rotate(
      Rotation(Rotations.Right),
      SpatialOrientation(Position(1, 1), Orientations.South)
    )
    assert(result.orientation === Orientations.West)
  }

  test("Rotate left from West to South") {
    val result = lawnMower.rotate(
      Rotation(Rotations.Left),
      SpatialOrientation(Position(1, 1), Orientations.West)
    )
    assert(result.orientation === Orientations.South)
  }

  test("Rotate right from West to North") {
    val result = lawnMower.rotate(
      Rotation(Rotations.Right),
      SpatialOrientation(Position(1, 1), Orientations.West)
    )
    assert(result.orientation === Orientations.North)
  }

  test("Move forward from North (1,1) -> (1,2)") {
    val result = lawnMower.move(
      Movement(Movements.Forward),
      SpatialOrientation(Position(1, 1), Orientations.North)
    )
    assert(result.position === Position(1, 2))
  }

  test("Move forward from East (1,1) -> (2,1)") {
    val result = lawnMower.move(
      Movement(Movements.Forward),
      SpatialOrientation(Position(1, 1), Orientations.East)
    )
    assert(result.position === Position(2, 1))
  }

  test("Move forward from South (1,1) -> (1,0)") {
    val result = lawnMower.move(
      Movement(Movements.Forward),
      SpatialOrientation(Position(1, 1), Orientations.South)
    )
    assert(result.position === Position(1, 0))
  }

  test("Move forward from West (1,1) -> (0,1)") {
    val result = lawnMower.move(
      Movement(Movements.Forward),
      SpatialOrientation(Position(1, 1), Orientations.West)
    )
    assert(result.position === Position(0, 1))
  }

  test("Move forward from North (4,4) -> (4,4) (out of lawn) should not move") {
    val result = lawnMower.move(
      Movement(Movements.Forward),
      SpatialOrientation(Position(4, 4), Orientations.North)
    )
    assert(result.position === Position(4, 4))
  }

  test("Move forward from East (4,4) -> (4,4) (out of lawn) should not move") {
    val result = lawnMower.move(
      Movement(Movements.Forward),
      SpatialOrientation(Position(4, 4), Orientations.East)
    )
    assert(result.position === Position(4, 4))
  }

  test(
    "isNextMovePossibleInLawn from (4,4) -> (4,5) (out of lawn) should return false"
  ) {
    val result = lawnMower.isNextMovePossibleInLawn(Position(4, 5))
    assert(result === false)
  }

  test("isNextMovePossibleInLawn from (4,4) -> (3,4) should return true") {
    val result = lawnMower.isNextMovePossibleInLawn(Position(3, 4))
    assert(result === true)
  }

  test("ComputeNexMove from (1,1) North -> (1,2) North") {
    val result = lawnMower.computeNextMove(
      Movement(Movements.Forward),
      SpatialOrientation(Position(1, 1), Orientations.North)
    )
    assert(result === SpatialOrientation(Position(1, 2), Orientations.North))
  }

  test("ComputeNexMove from (1,1) West -> (0,1) West") {
    val result = lawnMower.computeNextMove(
      Movement(Movements.Forward),
      SpatialOrientation(Position(1, 1), Orientations.West)
    )
    assert(result === SpatialOrientation(Position(0, 1), Orientations.West))
  }

  test("ComputeNexMove from (1,1) East -> (2,1) East") {
    val result = lawnMower.computeNextMove(
      Movement(Movements.Forward),
      SpatialOrientation(Position(1, 1), Orientations.East)
    )
    assert(result === SpatialOrientation(Position(2, 1), Orientations.East))
  }

  test("ComputeNexMove from (1,1) South -> (1,0) South") {
    val result = lawnMower.computeNextMove(
      Movement(Movements.Forward),
      SpatialOrientation(Position(1, 1), Orientations.South)
    )
    assert(result === SpatialOrientation(Position(1, 0), Orientations.South))
  }

  test("PerformAction Movement should move forward") {
    val result = lawnMower.performAction(
      Movement(Movements.Forward),
      SpatialOrientation(Position(1, 1), Orientations.South)
    )
    assert(result === SpatialOrientation(Position(1, 0), Orientations.South))
  }

  test("PerformAction Rotation should rotate") {
    val result = lawnMower.performAction(
      Rotation(Rotations.Left),
      SpatialOrientation(Position(1, 1), Orientations.South)
    )
    assert(result === SpatialOrientation(Position(1, 1), Orientations.East))
  }

  test("Mowing the lawn without actions should return the initial position") {
    val result = lawnMower.mowTheLawn(List())
    println(result)
    assert(
      result === List(SpatialOrientation(Position(2, 2), Orientations.North))
    )
  }

  test("Mowing the lawn with one action should return the new position") {
    val result = lawnMower.mowTheLawn(List(Movement(Movements.Forward)))
    assert(
      result === List(
        SpatialOrientation(Position(2, 2), Orientations.North),
        SpatialOrientation(Position(2, 3), Orientations.North)
      )
    )
  }

  test("Mowing the lawn outside the lawn should not move further") {
    val result = lawnMower.mowTheLawn(
      List(
        Movement(Movements.Forward),
        Movement(Movements.Forward),
        Movement(Movements.Forward),
        Movement(Movements.Forward),
        Movement(Movements.Forward)
      )
    )
    println(result)
    assert(
      result === List(
        SpatialOrientation(Position(2, 2), Orientations.North),
        SpatialOrientation(Position(2, 3), Orientations.North),
        SpatialOrientation(Position(2, 4), Orientations.North),
        SpatialOrientation(Position(2, 4), Orientations.North),
        SpatialOrientation(Position(2, 4), Orientations.North),
        SpatialOrientation(Position(2, 4), Orientations.North)
      )
    )
  }
}
