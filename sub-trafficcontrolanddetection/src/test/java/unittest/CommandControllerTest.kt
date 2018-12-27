package unittest

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.AuthenticationService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.CommandService
import at.jku.trafficcontrol.trafficcontrolanddetection.controller.CommandController
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.Command
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.ws.rs.core.Response

class CommandControllerTest {

    /**
     * Given
     *      a mocked command, authorized user and control system authority
     * When
     *      postSystemCommand is called
     * Then
     *     200 OK should be returned and applyControlSystemCommand should be called.
     */
    @Test
    fun postSystemCommand_CorrectControlSystemPayload_ShouldReturn200OK() {
        // Arrange
        val commandService = MockedCommandService()
        val classUnderTest = createWithDependencies(commandService)

        // Act
        val actualResponse = classUnderTest.postSystemCommand("controlsystem", "correct", Command())

        // Assert
        Assertions.assertEquals(Response.Status.OK, actualResponse.statusInfo)
        Assertions.assertTrue(commandService.calledControlSystemCommand)
        Assertions.assertFalse(commandService.calledSupervisorCommand)
    }

    /**
     * Given
     *      a mocked command, authorized user and supervisor authority
     * When
     *      postSystemCommand is called
     * Then
     *     200 OK should be returned and applyControlSuperVisorCommand should be called.
     */
    @Test
    fun postSystemCommand_CorrectSupervisorPayload_ShouldReturn200OK() {
        // Arrange
        val commandService = MockedCommandService()
        val classUnderTest = createWithDependencies(commandService)

        // Act
        val actualResponse = classUnderTest.postSystemCommand("trafficsupervisor", "correct", Command())

        // Assert
        Assertions.assertEquals(Response.Status.OK, actualResponse.statusInfo)
        Assertions.assertFalse(commandService.calledControlSystemCommand)
        Assertions.assertTrue(commandService.calledSupervisorCommand)
    }

    /**
     * Given
     *      a mocked command, authorized user and wrong authority
     * When
     *      postSystemCommand is called
     * Then
     *     400 BadRequest should be returned.
     */
    @Test
    fun postSystemCommand_CorrectUnknownAuthorityPayload_ShouldReturn400BadRequest() {
        // Arrange
        val commandService = MockedCommandService()
        val classUnderTest = createWithDependencies(commandService)

        // Act
        val actualResponse = classUnderTest.postSystemCommand("master", "correct", Command())

        // Assert
        Assertions.assertEquals(Response.Status.BAD_REQUEST, actualResponse.statusInfo)
        Assertions.assertFalse(commandService.calledControlSystemCommand)
        Assertions.assertFalse(commandService.calledSupervisorCommand)
    }

    /**
     * Given
     *      a mocked command, unauthorized user and supervisor authority
     * When
     *      postSystemCommand is called
     * Then
     *     401 UnAuthorized should be returned.
     */
    @Test
    fun postSystemCommand_UnAuthorizedSupervisorAuthorityPayload_ShouldReturn401UnAuthorized() {
        // Arrange
        val commandService = MockedCommandService()
        val authService = MockedAuthenticationService(false)
        val classUnderTest = createWithDependencies(commandService, authService)

        // Act
        val actualResponse = classUnderTest.postSystemCommand("master", "correct", Command())

        // Assert
        Assertions.assertEquals(Response.Status.UNAUTHORIZED, actualResponse.statusInfo)
        Assertions.assertFalse(commandService.calledControlSystemCommand)
        Assertions.assertFalse(commandService.calledSupervisorCommand)
    }

    companion object {
        fun createWithDependencies(commandService: CommandService = MockedCommandService(), authenticationService: AuthenticationService = MockedAuthenticationService()): CommandController {
            return CommandController(commandService, authenticationService)
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

    class MockedCommandService(var calledControlSystemCommand: Boolean = false, var calledSupervisorCommand: Boolean = false) : CommandService {
        /**
         * Applies a new command with control system authority.
         */
        override fun applyControlSystemCommand(command: Command) {
            calledControlSystemCommand = true
        }

        /**
         * Applies a new command with traffic supervisor authority.
         */
        override fun applySupervisorCommand(command: Command) {
            calledSupervisorCommand = true
        }
    }
}