package projet.`enum`

object Rotations extends Enumeration {
  type Rotation = RotationEnum
  val Left: RotationEnum = RotationEnum(90)
  val Right: RotationEnum = RotationEnum(-90)

  case class RotationEnum(degAngle: Int) extends super.Val

  def nameToRotation(name: Char): Option[Rotation] = {
    name match {
      case 'G' => Some(Left)
      case 'D' => Some(Right)
      case _   => None
    }
  }

}
