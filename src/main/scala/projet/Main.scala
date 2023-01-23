package projet

import projet.IO.{FileParser, FileWriter}
import projet.application.Transcriptor

object Main extends App {
  val fileParser = new FileParser()
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
