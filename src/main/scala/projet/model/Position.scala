package projet.model

case class Position(x: Int, y: Int) {
  def moveOf(x: Int, y: Int): Position = {
    Position(this.x + x, this.y + y)
  }
}
