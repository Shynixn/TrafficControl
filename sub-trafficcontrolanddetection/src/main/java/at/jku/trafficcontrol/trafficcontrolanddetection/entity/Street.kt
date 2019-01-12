package at.jku.trafficcontrol.trafficcontrolanddetection.entity

/**
 * Street.
 */
class Street(
        /**
         * Street id.
         */
        id: Int, startNode: Node, endNode: Node,
        /**
         * Amount of cars on the street.
         */
        var amountOfCars: Int = 0,

        /**
         * Max amount of cars this street should be holding.
         */
        var maxCars: Int = 10,

        /**
         *  Optional Traffic light to access this street.
         */
        var enterTrafficLight: TrafficLight? = null,

        /**
         * Speed limit.
         */
        var speedLimit: Double = 100.0
) : Edge(id, startNode, endNode)