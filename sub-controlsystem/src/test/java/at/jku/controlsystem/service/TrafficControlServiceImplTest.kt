package at.jku.controlsystem.service

import org.junit.jupiter.api.Test
import javax.ws.rs.core.Response
import kotlin.test.assertNotNull
import kotlin.test.fail

class TrafficControlServiceImplTest{

    @Test
    fun `Return value not null`(){
        val trafficControlService: TrafficApiService = TrafficApiServiceImpl()

        assertNotNull(trafficControlService.getCurrentTraffic())
    }

    @Test
    fun `Authorize request`(){
        val trafficControlService: TrafficApiService = TrafficApiServiceImpl()
        val response = trafficControlService.getCurrentTraffic()

        assertNotNull(response)

        val responseCode = response.getInt(ResponseError().code, -1)

        if(responseCode == Response.Status.UNAUTHORIZED.statusCode){
            fail("Request must be authorized")
        }else if (responseCode == Response.Status.NOT_FOUND.statusCode){
            fail("Server was not reachable")
        }
    }
}