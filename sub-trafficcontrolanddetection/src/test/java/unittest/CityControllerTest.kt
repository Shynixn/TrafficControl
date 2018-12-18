package unittest

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.CityService
import at.jku.trafficcontrol.trafficcontrolanddetection.controller.CityController
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.City
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.ws.rs.core.Response

class CityControllerTest {
    /**
     * Given
     *      a mocked city
     * When
     *      getCity is called
     * Then
     *     200 OK with city payload should be returned.
     */
    @Test
    fun getCity_MockedCity_ShouldReturn200OK() {
        // Arrange
        val classUnderTest = createWithDependencies()
        val expectedCity = City("MockedCity", "Springfield")

        // Act
        val actualResponse = classUnderTest.getCity()
        val city = actualResponse.entity as City

        // Assert
        Assertions.assertEquals(Response.Status.OK, actualResponse.statusInfo)
        Assertions.assertEquals(expectedCity.name, city.name)
        Assertions.assertEquals(expectedCity.displayName, city.displayName)
    }

    companion object {
        fun createWithDependencies(cityService: CityService? = null): CityController {
            if (cityService == null) {
                return CityController(MockedCityService())
            }


            return CityController()
        }
    }

    class MockedCityService : CityService {
        private val city = City("MockedCity", "Springfield")

        /**
         * Gets the currently managed city by the city service.
         */
        override fun getMainCity(): City {
            return city
        }
    }
}