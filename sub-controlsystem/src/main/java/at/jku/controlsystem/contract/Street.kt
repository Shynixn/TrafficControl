package at.jku.controlsystem.contract

class Street(
        var name: String,
        var currentTrafficRate: Double,
        var defaultTrafficRate: Double,
        var currentSpeed: Double,
        var defaultSpeed: Double,
        override var startNode: Node?,
        override var endNode: Node?) : Edge{

    override fun hashCode(): Int {
        return name.hashCode() +
                currentTrafficRate.hashCode() +
                defaultTrafficRate.hashCode() +
                currentSpeed.hashCode() +
                defaultSpeed.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}