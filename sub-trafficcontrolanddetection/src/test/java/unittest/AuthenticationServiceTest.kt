package unittest

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.AuthenticationService
import at.jku.trafficcontrol.trafficcontrolanddetection.service.AuthenticationServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AuthenticationServiceTest {
    /**
     * Given
     *      a correct base64 payload
     * When
     *      isAuthenticated is called
     * Then
     *     True should be returned.
     */
    @Test
    fun isAuthenticated_CorrectPayload_ShouldReturnTrue() {
        // Arrange
        val classUnderTest = createWithDependencies()

        // Act
        val isAuthorized = classUnderTest.isAuthenticated("Basic bWFzdGVyLXVzZXI6aG9yc2Utc3RhcGxl")

        // Assert
        Assertions.assertTrue(isAuthorized)
    }

    /**
     * Given
     *      a wrong base64 payload
     * When
     *      isAuthenticated is called
     * Then
     *     False should be returned.
     */
    @Test
    fun isAuthenticated_WrongPayload_ShouldReturnFalse() {
        // Arrange
        val classUnderTest = createWithDependencies()

        // Act
        val isAuthorized = classUnderTest.isAuthenticated("Basic bWFzdGVyLXVzZUI6aG9yc2Utc3RhcGxl")

        // Assert
        Assertions.assertFalse(isAuthorized)
    }

    /**
     * Given
     *      a null base64 payload
     * When
     *      isAuthenticated is called
     * Then
     *     False should be returned.
     */
    @Test
    fun isAuthenticated_NullPayload_ShouldReturnFalse() {
        // Arrange
        val classUnderTest = createWithDependencies()

        // Act
        val isAuthorized = classUnderTest.isAuthenticated(null)

        // Assert
        Assertions.assertFalse(isAuthorized)
    }

    companion object {
        fun createWithDependencies(): AuthenticationService {
            return AuthenticationServiceImpl()
        }
    }
}