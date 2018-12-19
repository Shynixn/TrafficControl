package at.jku.controlsystem.service

import javax.enterprise.context.RequestScoped
import javax.enterprise.inject.Default
import javax.json.JsonObject

@Default
@RequestScoped
class CommandApiServiceImpl: CommandApiService{
    override fun postBlockRoad(jsonObject: JsonObject) {

    }

    override fun postChangeTrafficLight(jsonObject: JsonObject) {

    }

}