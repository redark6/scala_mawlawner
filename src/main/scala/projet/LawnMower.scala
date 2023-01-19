package projet
import projet.model.{
  Action,
  Lawn,
  Movement,
  Position,
  Rotation,
  SpatialOrientation
}

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
      case _           => currentPosition
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
    (nextPosition.x >= 0) && (nextPosition.x <= this.lawn.widthBorder) &&
    (nextPosition.y >= 0) && (nextPosition.y <= this.lawn.heightBorder)
  }

  def computeNextMove(
      movement: Movement,
      currentPosition: SpatialOrientation
  ): SpatialOrientation = {
    val movementAction = movement.movement
    val orientation = currentPosition.orientation

    currentPosition.copy(
      position = currentPosition.position.moveOf(
        round(cos(orientation.radAngle)).toInt * movementAction.shift,
        round(sin(orientation.radAngle)).toInt * movementAction.shift
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
