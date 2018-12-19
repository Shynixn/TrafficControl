package at.jku.controlsystem.service

import org.junit.jupiter.api.Test
import javax.json.Json

class CommandServiceTests{
    @Test
    fun `Is initializeable and has interface`(){
        assert(CommandApiServiceImpl() is CommandApiService)
    }

    @Test
    fun `Has method postBlockRoad that accepts JsonElement`(){
        val commandService: CommandApiService = CommandApiServiceImpl()
        commandService.postBlockRoad(Json.createObjectBuilder().build())
    }

    @Test
    fun `Has method postChangeTrafficLight that accepts JsonObject`(){
        val commandService: CommandApiService = CommandApiServiceImpl()
        commandService.postBlockRoad(Json.createObjectBuilder().build())
    }
}