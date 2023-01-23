package projet.model

import play.api.libs.json.{
  JsArray,
  JsNumber,
  JsObject,
  JsString,
  JsValue,
  Writes
}

case class MowerDataResult(
    debut: SpatialOrientation,
    instructions: List[String],
    fin: SpatialOrientation
)

case class LawnMowingResult(
    limite: Position,
    tondeuses: List[MowerDataResult]
)

object LawnMowingResult {

  implicit object LawnMowingResultWrites extends Writes[LawnMowingResult] {

    def getPoint(p: Position): JsValue = {
      JsObject(
        List(
          "x" -> JsNumber(p.x),
          "y" -> JsNumber(p.y)
        )
      )
    }

    def getSpatialOrientation(
        spatialOrientation: SpatialOrientation
    ): JsValue = {
      JsObject(
        List(
          "point"     -> getPoint(spatialOrientation.position),
          "direction" -> JsString(spatialOrientation.orientation.name)
        )
      )
    }

    override def writes(r: LawnMowingResult): JsValue =
      JsObject(
        List(
          "limite" -> getPoint(r.limite),
          "tondeuses" -> JsArray(
            r.tondeuses.map(t => {
              JsObject(
                List(
                  "debut" -> getSpatialOrientation(t.debut),
                  "instructions" -> JsArray(
                    t.instructions.map(i => JsString(i))
                  ),
                  "fin" -> getSpatialOrientation(t.fin)
                )
              )
            })
          )
        )
      )
  }
}
