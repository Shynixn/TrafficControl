package at.jku.trafficparticipants.contract

data class Crossing(override val edges: MutableList<Edge> = mutableListOf()): Node