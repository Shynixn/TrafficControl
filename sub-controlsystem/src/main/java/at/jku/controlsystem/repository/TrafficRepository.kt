package at.jku.controlsystem.repository

import javax.json.Json
import javax.json.JsonObject


class TrafficRepository{
    var currentTraffic: JsonObject = Json.createObjectBuilder().build()

    companion object {
        private var _instance: TrafficRepository = TrafficRepository()

        val instance: TrafficRepository = _instance
    }


}