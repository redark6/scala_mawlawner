package projet
import better.files._

class FileTranscriptor {
  val f = File("/User/johndoe/Documents") // using constructor

  // pour ajouter du contenu dans un fichier ligne par ligne
  f.createIfNotExists()
    .appendLine() // on rajoute une ligne vide
    .appendLines("My name is", "Inigo Montoya") // on ajoute 2 nouvelles lignes

  // pour écraser le contenu du fichier
  f.createIfNotExists().overwrite("hello")
  /*
  def exportToJson(obj: Any, filePath: String) = {
    //val jsonString = JSONObject(obj.asInstanceOf[Map[String, Any]]).toString()
    //val file = new File(filePath)
    //val bw = new BufferedWriter(new FileWriter(file))
    //bw.write(jsonString)
    //bw.close()
  }

  def exportToCsv(obj: Any, filePath: String) = {
    //val jsonString = JSONObject(obj.asInstanceOf[Map[String, Any]]).toString()
    //val file = new File(filePath)
    //val bw = new BufferedWriter(new FileWriter(file))
    //bw.write(jsonString)
    //bw.close()
  }

 */
}
