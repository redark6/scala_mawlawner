package projet.model

import projet.`enum`.{Movements, Rotations}

sealed trait Action

case class Movement(movement: Movements.Movement) extends Action
case class Rotation(rotation: Rotations.Rotation) extends Action
