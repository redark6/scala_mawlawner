package projet.application

import play.api.libs.json.{JsValue, Json}
import projet.model.LawnMowingResult

class Transcriptor {

  def transcriptInJson(lawnMowingResult: LawnMowingResult): JsValue = {
    Json.toJson(lawnMowingResult)
  }

  def transcriptInCsv(lawnMowingResult: LawnMowingResult): List[String] = {
    lawnMowingResult.tondeuses
      .zip(1 to lawnMowingResult.tondeuses.length)
      .map(
        t => {
          s"${t._2.toString};${t._1.debut.position.x.toString};${t._1.debut.position.y.toString};${t._1.debut.orientation.name};${t._1.fin.position.x.toString};${t._1.fin.position.y.toString};${t._1.fin.orientation.name};${t._1.instructions.mkString}"
        }
      )
  }
}
