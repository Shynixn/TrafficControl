package at.jku.controlsystem.mock

import at.jku.controlsystem.service.mock.TrafficApiServiceMock
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class TrafficApiServiceMockTests{
    @Test
    fun `CityMock is initialized`(){
        val trafficApiService = TrafficApiServiceMock()
        assertNotNull(trafficApiService.cityMock)
    }
}