package at.jku.trafficcontrol.trafficcontrolanddetection.entity

/**
 * Node.
 */
class Node(
        /**
         * Node id.
         */
        var id: Int) {

    /**
     * Edges of the node.
     */
    val edges: MutableList<Edge> = ArrayList()
}