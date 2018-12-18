package at.jku.controlsystem.service

import javax.json.JsonObject

interface TrafficApiService{
    fun getCurrentTraffic(): JsonObject
}