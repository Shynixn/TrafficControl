package at.jku.controlsystem.service

import at.jku.controlsystem.entity.IncidentResponse

interface RoadMaintenanceService{
    fun getIncidents(): List<IncidentResponse>
}