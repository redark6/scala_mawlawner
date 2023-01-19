package projet

import better.files._
import com.typesafe.config.{Config, ConfigFactory}
import projet.`enum`.{Movements, Orientations, Rotations}
import projet.exception.DonneesIncorectesException
import projet.model.{
  Action,
  Lawn,
  Movement,
  Position,
  Rotation,
  SpatialOrientation
}

@SuppressWarnings(Array("org.wartremover.warts.Throw"))
class FileParser {
  val conf: Config = ConfigFactory.load()
  val inputFilePath: String = conf.getString("appplication.input-file")

  def getFileContent(): List[String] = {
    val file = File(inputFilePath)
    val fileLines = file.lines.toList
    fileLines.size match {
      case 0 => throw DonneesIncorectesException(s"file is empty")
      case 5 != 0 =>
        throw DonneesIncorectesException(s"file incorect data")
      case _ => fileLines
    }
  }

  def getField(line: Option[String]) = {
    line match {
      case Some(data) =>
        val coord = data.split(" ")
        Lawn(coord.head.toInt, coord.last.toInt)
      case _ =>
        throw DonneesIncorectesException(
          s"Orientation is not defined. Only N,E,S,W enabled."
        )
    }
  }

  def estt(): Unit = {
    val fileLines = getFileContent()
    val lawn = getField(fileLines.headOption)
    // boucle
    val fileLines2 = fileLines.drop(1)
    println(fileLines2)
    for (a <- fileLines2.indices by 2) {
      val coordinate = fileLines2(a).toList.filter(_ != ' ')
      val movements1 = fileLines2(a + 1).toList

      val movements: List[Action] = movements1.map(m => {
        val mo = Movements.nameToMovement(m)
        val or = Rotations.nameToRotation(m)
        (mo, or) match {
          case (Some(movement), None) => Movement(movement)
          case (None, Some(rotation)) => Rotation(rotation)
          case _ =>
            throw DonneesIncorectesException(
              s"Not a movment nor a rotation"
            )
        }
      })

      println(coordinate)
      println(movements)

      println(Position(coordinate(0).asDigit, coordinate(1).asDigit))
      val tondeuse = new LawnMower(
        SpatialOrientation(
          Position(coordinate(0).asDigit, coordinate(1).asDigit),
          Orientations
            .nameToOrientation(coordinate(2))
            .getOrElse(
              throw DonneesIncorectesException(
                s"Orientation is not defined. Only N,E,S,W enabled."
              )
            )
        ),
        lawn
      )
      println("/////////////////////////////")
      val res = tondeuse.mowTheLawn(movements)
      println(res)
    }
    // si on veut récupérer tout le contenu du fichier en String
    //f.contentAsString
  }

}
