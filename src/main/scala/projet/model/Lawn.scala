package projet.model

case class Lawn(height: Int, width: Int) {

  def heightBorder: Int = {
    height - 1
  }

  def widthBorder: Int = {
    width - 1
  }
}
