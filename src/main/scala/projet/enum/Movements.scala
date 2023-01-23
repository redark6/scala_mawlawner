package projet.`enum`

object Movements extends Enumeration {
  type Movement = MovementEnum
  val Forward: MovementEnum = MovementEnum("A", 1)

  case class MovementEnum(name: String, shift: Int) extends super.Val

  def nameToMovement(name: Char): Option[Movement] = {
    name match {
      case 'A' => Some(Forward)
      case _   => None
    }
  }
}
