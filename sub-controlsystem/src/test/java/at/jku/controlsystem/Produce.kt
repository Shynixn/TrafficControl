package at.jku.controlsystem

import at.jku.controlsystem.contract.Crossing
import at.jku.controlsystem.contract.Street

class Produce{
    fun defaultStreet(): Street {
        val name = "Street McStreetface"
        val defaultTrafficRate = 1.0
        val defaultSpeed = 1.0
        val startNode = Crossing()
        val endNode = Crossing()

        return Street(
                name,
                defaultTrafficRate,
                defaultTrafficRate,
                defaultSpeed,
                defaultSpeed,
                startNode,
                endNode
        )
    }
}