package at.jku.controlsystem.service

import at.jku.controlsystem.parser.CityParser
import at.jku.controlsystem.service.mock.TrafficApiServiceMock
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.json.Json
import javax.json.JsonObject
import kotlin.test.assertNotNull


class TrafficControlServiceTest{


    lateinit var trafficApiFactory: TrafficApiFactory

    @BeforeEach
    fun initialize(){
        trafficApiFactory = TrafficApiFactory()
        trafficApiFactory.setTrafficApiSerivce(TrafficApiServiceMock());
    }

    @Test
    fun `Is initializeable and has interface`() {
        assert(trafficApiFactory.getTrafficApiService() is TrafficApiService)
    }

    @Test
    fun `Has method postCurrentTraffic that accepts JsonObject`(){
        val trafficApiService = trafficApiFactory.getTrafficApiService()
        val mockRequestBuilder = Json.createObjectBuilder()
    }

    @Test
    fun `Has method getTrafficCommands that returns JsonObject`(){
        val trafficApiService = trafficApiFactory.getTrafficApiService()
        val response: JsonObject = trafficApiService.getCurrentTraffic()

        assertNotNull(response)
    }

    @Test
    fun `getTrafficCommands is parseable to Street`(){
        val trafficApiService = trafficApiFactory.getTrafficApiService()
        val response: JsonObject = trafficApiService.getCurrentTraffic()
        val rootStreet = CityParser().fromJson(response)

        assertNotNull(rootStreet)
        assertNotNull(rootStreet.startNode)
    }

}