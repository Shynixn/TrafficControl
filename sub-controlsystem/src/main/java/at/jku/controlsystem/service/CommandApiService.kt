package at.jku.controlsystem.service

import javax.json.JsonObject

interface CommandApiService{
    fun postBlockRoad(jsonObject: JsonObject)
    fun postChangeTrafficLight(jsonObject: JsonObject)

}