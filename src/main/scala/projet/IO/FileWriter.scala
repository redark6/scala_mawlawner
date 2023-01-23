package projet.IO

import play.api.libs.json.JsValue

class FileWriter {

  def writeResultsInJson(result: JsValue): Unit = {
    println(result)
  }

  def writeResultsInCsv(result: List[String]): Unit = {
    result.foreach(s => println(s))
  }
}
