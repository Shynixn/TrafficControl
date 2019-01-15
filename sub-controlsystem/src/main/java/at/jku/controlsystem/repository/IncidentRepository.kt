package at.jku.controlsystem.repository

import at.jku.controlsystem.entity.IncidentResponse

class IncidentRepository{
    companion object {
        val instance = IncidentRepository()
    }

    var incidents = emptyList<IncidentResponse>()
}