package progfun

import projet.FileParser

object Main extends App {
  println("Ici le programme principal")
  val t = new FileParser()
  t.estt()
  // Le code suivant ne compilera pas.
  // var tmp = null;
  // var tmp2 = if (tmp == 1) "yes" else 1

  // println(s"tmp: $tmp, tmp2: $tmp2")
}
