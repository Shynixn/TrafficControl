package at.jku.controlsystem

import at.jku.controlsystem.service.TrafficApiService
import at.jku.controlsystem.service.TrafficApiServiceImpl
import org.junit.jupiter.api.Test
import javax.json.Json
import javax.json.JsonObject
import kotlin.test.assertNotNull


class TrafficControlServiceTest{
    @Test
    fun `Is initializeable and has interface`() {
        assert(TrafficApiServiceImpl() is TrafficApiService)
    }

    @Test
    fun `Has method postCurrentTraffic that accepts JsonObject`(){
        val trafficApiService = TrafficApiServiceImpl()
        val mockRequestBuilder = Json.createObjectBuilder()

        trafficApiService.postCurrentTraffic(mockRequestBuilder.build())
    }

    @Test
    fun `Has method getTrafficCommands that returns JsonObject`(){
        val trafficApiService = TrafficApiServiceImpl()
        val response: JsonObject = trafficApiService.getTrafficCommands()

        assertNotNull(response)
    }
}