package projet.`enum`

object Rotations extends Enumeration {
  type Rotation = RotationEnum
  val Left: RotationEnum = RotationEnum("G", 90)
  val Right: RotationEnum = RotationEnum("D", -90)

  case class RotationEnum(name: String, degAngle: Int) extends super.Val

  def nameToRotation(name: Char): Option[Rotation] = {
    name match {
      case 'G' => Some(Left)
      case 'D' => Some(Right)
      case _   => None
    }
  }

}
