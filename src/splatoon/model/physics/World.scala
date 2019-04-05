package splatoon.model.physics

import splatoon.model.Player

class World(var gravity:Double) {

  var players: List[Player] = List()
  var boundaries:List[Boundary] = List()
}
