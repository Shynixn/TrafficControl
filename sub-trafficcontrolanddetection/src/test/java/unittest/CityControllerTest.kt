package unittest

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.AuthenticationService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.CityService
import at.jku.trafficcontrol.trafficcontrolanddetection.controller.CityController
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.City
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.concurrent.CompletableFuture
import javax.ws.rs.core.Response

class CityControllerTest {
    /**
     * Given
     *      a mocked city and authorized user
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
        val actualResponse = classUnderTest.getCity("")
        val city = actualResponse.entity as City

        // Assert
        Assertions.assertEquals(Response.Status.OK, actualResponse.statusInfo)
        Assertions.assertEquals(expectedCity.name, city.name)
        Assertions.assertEquals(expectedCity.displayName, city.displayName)
    }

    /**
     * Given
     *      a mocked city and unauthorized user
     * When
     *      getCity is called
     * Then
     *     401 UnAuthorized should be returned.
     */
    @Test
    fun getCity_MockedCityUnAuthorizedUser_ShouldReturn401UnAuthorized() {
        // Arrange
        val classUnderTest = createWithDependencies(MockedCityService(), MockedAuthenticationService(false))

        // Act
        val actualResponse = classUnderTest.getCity("")

        // Assert
        Assertions.assertEquals(Response.Status.UNAUTHORIZED, actualResponse.statusInfo)
    }

    companion object {
        fun createWithDependencies(cityService: CityService = MockedCityService(), authenticationService: AuthenticationService = MockedAuthenticationService()): CityController {
            return CityController(cityService, authenticationService)
        }
    }

    class MockedAuthenticationService(private val authenticated: Boolean = true) : AuthenticationService {
        /**
         * Does the given [base64EncodedText] authorize a user?
         */
        override fun isAuthenticated(base64EncodedText: String?): Boolean {
            return authenticated
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

        /**
         * Refreshes the state of the city with the latest collected information of traffic participants.
         */
        override fun refreshCityTrafficParticipants(): CompletableFuture<Void?> {
            return CompletableFuture()
        }
    }
}