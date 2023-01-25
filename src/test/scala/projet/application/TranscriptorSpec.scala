package projet.application

import org.scalatest.funsuite.AnyFunSuite
import projet.`enum`.Orientations
import projet.model.{
  LawnMowingResult,
  MowerDataResult,
  Position,
  SpatialOrientation
}

class TranscriptorSpec extends AnyFunSuite {

  val transcriptor = new Transcriptor()
  val lawnMowingResult: LawnMowingResult = LawnMowingResult(
    Position(5, 5),
    List(
      MowerDataResult(
        SpatialOrientation(Position(1, 2), Orientations.North),
        List("G", "A", "G", "A", "G", "A", "G", "A", "A", "D"),
        SpatialOrientation(Position(1, 3), Orientations.East)
      ),
      MowerDataResult(
        SpatialOrientation(Position(2, 4), Orientations.North),
        List(
          "D",
          "D",
          "A",
          "G",
          "A",
          "A",
          "D",
          "A",
          "D",
          "A",
          "A",
          "G",
          "D",
          "A",
          "A"
        ),
        SpatialOrientation(Position(0, 2), Orientations.West)
      )
    )
  )

  test("Transcripting in json should return a json string") {
    val result = transcriptor.transcriptInJson(lawnMowingResult).toString()
    assert(
      result === """{"limite":{"x":5,"y":5},"tondeuses":[{"debut":{"point":{"x":1,"y":2},"direction":"N"},"instructions":["G","A","G","A","G","A","G","A","A","D"],"fin":{"point":{"x":1,"y":3},"direction":"E"}},{"debut":{"point":{"x":2,"y":4},"direction":"N"},"instructions":["D","D","A","G","A","A","D","A","D","A","A","G","D","A","A"],"fin":{"point":{"x":0,"y":2},"direction":"W"}}]}"""
    )
  }

  test("Transcripting in csv should return a list of string") {
    val result = transcriptor.transcriptInCsv(lawnMowingResult)
    assert(
      result === List(
        "1;1;2;N;1;3;E;GAGAGAGAAD",
        "2;2;4;N;0;2;W;DDAGAADADAAGDAA"
      )
    )
  }
}
