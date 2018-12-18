package at.jku.controlsystem.contract

data class Street(
        var name: String,
        var currentTrafficRate: Double,
        var defaultTrafficRate: Double,
        var currentSpeed: Double,
        var defaultSpeed: Double,
        override var startNode: Node,
        override var endNode: Node) : Edge