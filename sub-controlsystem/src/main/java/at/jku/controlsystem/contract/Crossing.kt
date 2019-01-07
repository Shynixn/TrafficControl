package at.jku.controlsystem.contract

data class Crossing(override val edges: MutableList<Edge> = mutableListOf()): Node