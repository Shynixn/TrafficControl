package at.jku.trafficcontrol.trafficcontrolanddetection.entity

/**
 * City Entity.
 */
class City(
        /**
         * Name of the city.
         */
        val name: String,
        /**
         *  Displayname of the city.
         */
        val displayName: String) {

    /**
     * List of nodes in the city.
     */
    val nodes: MutableList<Node> = ArrayList()

    /**
     * List of edges in the city.
     */
    val edges: MutableList<Edge> = ArrayList()

    /**
     * Default commands.
     */
    val defaultCommands: MutableList<Command> = ArrayList()

    /**
     * ControlSystem commands.
     */
    val customCommands: MutableList<Command> = ArrayList()
}