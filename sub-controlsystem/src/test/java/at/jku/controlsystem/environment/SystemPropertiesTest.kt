package at.jku.controlsystem.environment

import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class SystemPropertiesTest{
    @Test
    fun `Test username and password are present`(){
        val username = System.getenv("TRAFFIC_CONTROL_USERNAME")
        val password = System.getenv("TRAFFIC_CONTROL_PASSWORD")

        assertNotNull(username, "Username")
        assertNotNull(password, "Password")
    }
}