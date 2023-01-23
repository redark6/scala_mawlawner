package projet.application

import projet.model._

import scala.annotation.tailrec
import scala.math._

class LawnMower(spatialOrientationStart: SpatialOrientation, lawn: Lawn) {

  def mowTheLawn(actions: List[Action]): List[SpatialOrientation] = {
    @tailrec
    def performActions(
        action: List[Action],
        currentPosition: SpatialOrientation,
        previousSpatialOrientation: List[SpatialOrientation]
    ): List[SpatialOrientation] = {
      action match {
        case head :: Nil =>
          previousSpatialOrientation :+ performAction(head, currentPosition)
        case head :: tail =>
          val nextPosition = performAction(head, currentPosition)
          performActions(
            tail,
            nextPosition,
            previousSpatialOrientation :+ nextPosition
          )
        case _ => previousSpatialOrientation
      }
    }
    performActions(actions, spatialOrientationStart, List())
  }

  def performAction(
      action: Action,
      currentPosition: SpatialOrientation
  ): SpatialOrientation = {
    action match {
      case m: Movement => this.move(m, currentPosition)
      case r: Rotation => this.rotate(r, currentPosition)
    }
  }

  def move(
      movement: Movement,
      currentPosition: SpatialOrientation
  ): SpatialOrientation = {
    val nextMove = this.computeNextMove(movement, currentPosition)
    if (this.isNextMovePossibleInLawn(nextMove.position)) {
      nextMove
    } else {
      currentPosition.copy()
    }
  }

  def isNextMovePossibleInLawn(nextPosition: Position): Boolean = {
    lawn.isInsideLawn(nextPosition)
  }

  def computeNextMove(
      movement: Movement,
      currentPosition: SpatialOrientation
  ): SpatialOrientation = {
    val shift = movement.movement.shift
    val radAngle = currentPosition.orientation.radAngle
    currentPosition.copy(
      position = currentPosition.position.moveOf(
        round(cos(radAngle)).toInt * shift,
        round(sin(radAngle)).toInt * shift
      )
    )
  }

  def rotate(
      rotation: Rotation,
      currentPosition: SpatialOrientation
  ): SpatialOrientation = {
    currentPosition.copy(
      orientation =
        currentPosition.orientation.rotate(rotation.rotation.degAngle)
    )
  }
}
