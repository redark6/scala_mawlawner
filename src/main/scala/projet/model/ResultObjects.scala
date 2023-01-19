package projet.model

case class Result(
    limite: Position,
    tondeuses: List[MowerResult]
)
case class MowerResult(
    debut: Position,
    instructions: List[Char],
    fin: Position
)
