package projet.model

import projet.`enum`.Orientations

case class SpatialOrientation(
    position: Position,
    orientation: Orientations.Orientation
)
