package projet.model

case class Lawn(height: Int, width: Int) {

  def heightBorder: Int = {
    height - 1
  }

  def widthBorder: Int = {
    width - 1
  }

  def isInsideLawn(position: Position): Boolean = {
    position.x >= 0 && position.x <= this.widthBorder && position.y >= 0 && position.y <= this.heightBorder
  }
}
