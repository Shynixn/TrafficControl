package unittest

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.ClientService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.TrafficParticipantsService
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.TrafficInformation
import at.jku.trafficcontrol.trafficcontrolanddetection.service.TrafficParticipantsServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.slf4j.Logger
import javax.ws.rs.client.Client
import javax.ws.rs.client.Invocation
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Response

class TrafficParticipantsServiceTest {
    /**
     * Given
     *  a mocked client
     * When
     *   a requestCamerasOfCity is called
     * Then
     *   a call to traffic participants should be sent and content be received.
     */
    @Test
    fun requestCamerasOfCity_MockedClient_ShouldReceiveCorrectPayload() {
        // Arrange
        val classUnderTest = createWithDependencies()

        // Act
        val result = classUnderTest.requestCamerasOfCity().get()
        val expectedTrafficInformation = result[1]

        // Assert
        Assertions.assertEquals(30, expectedTrafficInformation.amountOfCars)
        Assertions.assertEquals(2, expectedTrafficInformation.streetId)
    }

    /**
     * Given
     *  a failing mocked client
     * When
     *   a requestCamerasOfCity is called
     * Then
     *   no content should be received.
     */
    @Test
    fun requestCamerasOfCity_MockedFailingClient_ShouldEmptyPayload() {
        // Arrange
        val classUnderTest = createWithDependencies {
            Response.status(Response.Status.BAD_REQUEST).build()
        }

        // Act
        val result = classUnderTest.requestCamerasOfCity().get()

        // Assert
        Assertions.assertEquals(0, result.size)
    }

    /**
     * Given
     *  a mocked client
     * When
     *   a  requestInductionLoopsOfCity is called
     * Then
     *   a call to traffic participants should be sent and content be received.
     */
    @Test
    fun requestInductionLoopsOfCity_MockedClient_ShouldReceiveCorrectPayload() {
        // Arrange
        val classUnderTest = createWithDependencies()

        // Act
        val result = classUnderTest.requestInductionLoopsOfCity().get()
        val expectedTrafficInformation = result[0]

        // Assert
        Assertions.assertEquals(20, expectedTrafficInformation.amountOfCars)
        Assertions.assertEquals(1, expectedTrafficInformation.streetId)
    }

    /**
     * Given
     *  a failing mocked client
     * When
     *   a  requestInductionLoopsOfCity is called
     * Then
     *   no content should be received.
     */
    @Test
    fun requestInductionLoopsOfCity_MockedFailingClient_ShouldReceiveEmptyPayload() {
        // Arrange
        val classUnderTest = createWithDependencies {
            Response.status(Response.Status.BAD_REQUEST).build()
        }

        // Act
        val result = classUnderTest.requestInductionLoopsOfCity().get()

        // Assert
        Assertions.assertEquals(0, result.size)
    }

    companion object {
        fun createWithDependencies(function: (() -> Response)? = {
            val result = arrayListOf(TrafficInformation(1, 20), TrafficInformation(2, 30), TrafficInformation(3, 0))
            Response.ok(result).build()
        }): TrafficParticipantsService {
            val logger = Mockito.mock(Logger::class.java)

            return TrafficParticipantsServiceImpl(MockedClientService(function!!), logger)
        }
    }

    class MockedClientService(private val function: () -> Response) : ClientService {
        /**
         * Creates a new client.
         */
        override fun createClient(): Client {
            val invocationBuilder = Mockito.mock(Invocation.Builder::class.java)
            Mockito.`when`(invocationBuilder.get()).thenAnswer {
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