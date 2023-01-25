package projet

import com.typesafe.config.{Config, ConfigFactory}
import projet.IO.{FileParser, FileWriter}
import projet.application.Transcriptor

object Main {
  def main(args: Array[String])(conf: Option[Config]): Unit = {
    val config = conf match {
      case Some(c) => c
      case None    => ConfigFactory.load()
    }
    val fileParser = new FileParser(config)
    val fileTranscriptor = new FileWriter()
    try {
      val lawnParsingResult = fileParser.parseFileToLawnMowingResult()
      val transcriptor = new Transcriptor()
      fileTranscriptor.writeResultsInJson(
        transcriptor.transcriptInJson(lawnParsingResult)
      )
      fileTranscriptor.writeResultsInCsv(
        transcriptor.transcriptInCsv(lawnParsingResult)
      )
    } catch {
      case e: Exception =>
        println(
          "Une erreur à été trouvé, interrruption du programme: " + e.getMessage
        )
    }
  }
}
