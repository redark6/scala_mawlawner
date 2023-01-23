package projet.`enum`

import scala.math._

object Orientations extends Enumeration {
  type Orientation = OrientationEnum
  val North: OrientationEnum = OrientationEnum("N", 90, Pi / 2)
  val East: OrientationEnum = OrientationEnum("E", 0, 0)
  val South: OrientationEnum = OrientationEnum("S", 270, (3 * Pi) / 2)
  val West: OrientationEnum = OrientationEnum("W", 180, Pi)

  protected case class OrientationEnum(
      name: String,
      degAngle: Int,
      radAngle: Double
  ) extends super.Val {
    def rotate(degAngle: Int): OrientationEnum = {
      Math.floorMod(this.degAngle + degAngle, 360) match {
        case 0   => East
        case 90  => North
        case 180 => West
        case 270 => South
        case _   => this
      }
    }
  }

  def nameToOrientation(name: Char): Option[Orientation] = {
    name match {
      case 'N' => Some(North)
      case 'E' => Some(East)
      case 'S' => Some(South)
      case 'W' => Some(West)
      case _   => None
    }
  }
}
