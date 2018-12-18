package at.jku.controlsystem

import at.jku.controlsystem.service.CommandApiService
import at.jku.controlsystem.service.CommandApiServiceImpl
import org.junit.jupiter.api.Test
import javax.json.Json

class CommandServiceTests{
    @Test
    fun `Is initializeable and has interface`(){
        assert(CommandApiServiceImpl() is CommandApiService)
    }

    @Test
    fun `Has method postBlockRoad that accepts JsonObject`(){
        val commandService: CommandApiService = CommandApiServiceImpl()
        val requestBuilder = Json.createObjectBuilder()
        commandService.postBlockRoad(requestBuilder.build())
    }

    @Test
    fun `Has method postChangeTrafficLight that accepts JsonObject`(){
        val commandService: CommandApiService = CommandApiServiceImpl()
        val requestBuilder = Json.createObjectBuilder()
        commandService.postChangeTrafficLight(requestBuilder.build())
    }
}