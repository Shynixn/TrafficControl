package unittest

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.ClientService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.LoggingService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.RoadMaintenanceService
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.Node
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.Street
import at.jku.trafficcontrol.trafficcontrolanddetection.service.RoadMaintenanceServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.slf4j.Logger
import javax.ws.rs.client.Client
import javax.ws.rs.client.Invocation
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Response

class RoadMaintenanceServiceTest {
    /**
     * Given
     *  a mocked client
     * When
     *   a reportAmountOfCars is called
     * Then
     *   a call to road maintenance should be sent.
     */
    @Test
    fun reportAmountOfCars_MockedClient_ShouldSendCorrectPayload() {
        // Arrange
        val streets = arrayListOf(Street(2, Node(1), Node(1)), Street(3, Node(1), Node(1)), Street(4, Node(1), Node(1)))
        val classUnderTest = createWithDependencies()

        // Act
        val result = classUnderTest.reportAmountOfCars(streets).get()

        // Assert
        Assertions.assertEquals(null, result)
    }

    /**
     * Given
     *  a failing mocked client
     * When
     *   a reportAmountOfCars is called
     * Then
     *   no content should be send.
     */
    @Test
    fun reportAmountOfCars_MockedFailingClient_ShouldFailSendingPayload() {
        // Arrange
        val streets = arrayListOf(Street(2, Node(1), Node(1)), Street(3, Node(1), Node(1)), Street(4, Node(1), Node(1)))
        val classUnderTest = createWithDependencies {
            Response.status(Response.Status.BAD_REQUEST).build()
        }

        // Act
        val result = classUnderTest.reportBlockStreets(streets).get()

        // Assert
        Assertions.assertEquals(null, result)
    }

    /**
     * Given
     *  a mocked client
     * When
     *   a reportBlockStreets is called
     * Then
     *   a call to road maintenance should be sent.
     */
    @Test
    fun reportBlockStreets_MockedClient_ShouldSendCorrectPayload() {
        // Arrange
        val streets = arrayListOf(Street(2, Node(1), Node(1)), Street(3, Node(1), Node(1)), Street(4, Node(1), Node(1)))
        val classUnderTest = createWithDependencies()

        // Act
        val result = classUnderTest.reportBlockStreets(streets).get()

        // Assert
        Assertions.assertEquals(null, result)
    }

    /**
     * Given
     *  a failing mocked client
     * When
     *   a reportBlockStreets is called
     * Then
     *   no content should be send.
     */
    @Test
    fun reportBlockStreets_MockedFailingClient_ShouldFailSendingPayload() {
        // Arrange
        val streets = arrayListOf(Street(2, Node(1), Node(1)), Street(3, Node(1), Node(1)), Street(4, Node(1), Node(1)))
        val classUnderTest = createWithDependencies {
            Response.status(Response.Status.BAD_REQUEST).build()
        }

        // Act
        val result = classUnderTest.reportBlockStreets(streets).get()

        // Assert
        Assertions.assertEquals(null, result)
    }

    companion object {
        fun createWithDependencies(function: (() -> Response)? = {
            Response.ok().build()
        }): RoadMaintenanceService {
            val logger = Mockito.mock(LoggingService::class.java)

            return RoadMaintenanceServiceImpl(MockedClientService(function!!), logger)
        }
    }

    class MockedClientService(private val function: () -> Response) : ClientService {
        /**
         * Creates a new client.
         */
        override fun createClient(): Client {
            val invocationBuilder = Mockito.mock(Invocation.Builder::class.java)
            Mockito.`when`(invocationBuilder.post(Mockito.any())).thenAnswer {
                function.invoke()
            }

            val webTarget = Mockito.mock(WebTarget::class.java)
            Mockito.`when`(webTarget.request()).thenReturn(invocationBuilder)

            val client = Mockito.mock(Client::class.java)
            Mockito.`when`(client.target(Mockito.anyString())).thenReturn(webTarget)


            return client
        }
    }
}