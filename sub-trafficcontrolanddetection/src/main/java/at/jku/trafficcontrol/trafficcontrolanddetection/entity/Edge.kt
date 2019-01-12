package at.jku.trafficcontrol.trafficcontrolanddetection.entity

/**
 * Edge.
 */
open class Edge(

        /**
         * Int.
         */
        var id: Int,

        /**
         * Start node.
         */
        var startNode: Node,
        /**
         * End node.
         */
        var endNode: Node)