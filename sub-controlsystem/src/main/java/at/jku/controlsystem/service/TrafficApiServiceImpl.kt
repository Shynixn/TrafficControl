package at.jku.controlsystem.service

import javax.json.Json
import javax.json.JsonObject

class TrafficApiServiceImpl : TrafficApiService{
    override fun postCurrentTraffic(build: JsonObject) {

    }

    override fun getTrafficCommands(): JsonObject {
        return Json.createObjectBuilder().build()
    }

}