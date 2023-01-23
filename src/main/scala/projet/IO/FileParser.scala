package projet.IO

import better.files._
import com.typesafe.config.{Config, ConfigFactory}
import projet.`enum`.{Movements, Orientations, Rotations}
import projet.application.LawnMower
import projet.exception.DonneesIncorectesException
import projet.model._

import scala.util.Try

@SuppressWarnings(Array("org.wartremover.warts.Throw"))
class FileParser {
  val conf: Config = ConfigFactory.load()
  val inputFilePath: String = conf.getString("appplication.input-file")

  def getFileLines: List[String] = {
    val file = File(inputFilePath)
    file.lines.toList match {
      case Nil => throw DonneesIncorectesException("Le fichier est vide")
      case lines =>
        lines.size match {
          case size if size == 1 || (size - 1) % 2 != 0 =>
            throw DonneesIncorectesException(
              "Le fichier ne contient pas le bon nombre de lignes"
            )
          case _ => lines
        }
    }
  }

  def parseLawn(str: String): Lawn = {
    val lawnSize = str
      .split(" ")
      .flatMap(v => Try(v.toInt).toOption)
      .flatMap(v => {
        if (v > 0) Some(v)
        else
          throw DonneesIncorectesException(
            "La taille de la pelouse doit être supérieure à 0"
          )
      })
    lawnSize.length match {
      case 2 => Lawn(lawnSize(0), lawnSize(1))
      case _ =>
        throw DonneesIncorectesException(
          "Le nombre de paramètres de la taille de la pelouse est incorrect"
        )
    }
  }

  def parseFile(): (Lawn, List[(SpatialOrientation, List[Action])]) = {
    val lines = this.getFileLines
    lines match {
      case lawnInfo :: mowersInfo =>
        val lawn = this.parseLawn(lawnInfo)
        val lawnMowersDatas = getLawnMowersDatas(lawn, mowersInfo)
        (lawn, lawnMowersDatas)
      case _ =>
        throw DonneesIncorectesException(
          "Une erreur est survenue dans la lors de la separation des données"
        )
    }
  }

  def parseActions(actions: String): List[Action] = {
    actions.toList
      .flatMap(v => {
        Movements.nameToMovement(v) match {
          case Some(movement) => Some(Movement(movement))
          case None =>
            Rotations.nameToRotation(v) match {
              case Some(rotation) => Some(Rotation(rotation))
              case _              => throw DonneesIncorectesException("Action inconnue")
            }
        }
      })
  }

  def getLawnMowersDatas(
      lawn: Lawn,
      datas: List[String]
  ): List[(SpatialOrientation, List[Action])] = {
    datas
      .grouped(2)
      .map {
        case List(position, actions) =>
          val spatialOrientation = this.parseSpatialOrientation(lawn, position)
          val actionsList = this.parseActions(actions)
          (spatialOrientation, actionsList)
      }
      .toList
  }

  def parseSpatialOrientation(
      lawn: Lawn,
      position: String
  ): SpatialOrientation = {
    position.toList.filter(_ != ' ') match {
      case x :: y :: orientation :: Nil =>
        (
          Try(x.asDigit).toOption,
          Try(y.asDigit).toOption,
          Orientations.nameToOrientation(orientation)
        ) match {
          case (Some(x), Some(y), Some(orientation)) =>
            if (lawn.isInsideLawn(Position(x, y))) {
              SpatialOrientation(Position(x, y), orientation)
            } else {
              throw DonneesIncorectesException(
                "La position de départ de la tondeuse est en dehors de la pelouse"
              )
            }
          case _ =>
            throw DonneesIncorectesException(
              "Les données de position de la tondeuse sont incorrectes"
            )
        }
      case _ =>
        throw DonneesIncorectesException(
          "Le nombre de paramètres de la position de la tondeuse est incorrect"
        )
    }
  }

  def parseFileToLawnMowingResult(): LawnMowingResult = {
    val (lawn, lawnMowersDatas) = this.parseFile()
    val lawnDataResult = lawnMowersDatas.map(v => {
      val lawnMowingResult = new LawnMower(v._1, lawn).mowTheLawn(v._2)
      (lawnMowingResult.headOption, lawnMowingResult.lastOption) match {
        case (Some(startingPosition), Some(finalPosition)) =>
          MowerDataResult(startingPosition, v._2.map(_.name), finalPosition)
        case (Some(startingPosition), None) =>
          MowerDataResult(startingPosition, v._2.map(_.name), startingPosition)
        case _ => throw DonneesIncorectesException("Erreur inconnue")
      }
    })
    LawnMowingResult(Position(lawn.width, lawn.height), lawnDataResult)
  }
}
