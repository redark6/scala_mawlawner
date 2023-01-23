package projet.model

import projet.`enum`.{Movements, Rotations}

sealed trait Action {
  def name: String
}

case class Movement(movement: Movements.Movement) extends Action {
  override def name: String = movement.name
}
case class Rotation(rotation: Rotations.Rotation) extends Action {
  override def name: String = rotation.name
}
