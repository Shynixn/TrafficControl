package at.jku.controlsystem.contract

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ContractTests{
    @Test
    fun `Street initializeable and is Edge`(){
        val name = "Street McStreetface"
        val defaultTrafficRate = 1.0
        val defaultSpeed = 1.0
        val startNode = Crossing()
        val endNode = Crossing()

        val street = Street(
                name,
                defaultTrafficRate,
                defaultTrafficRate,
                defaultSpeed,
                defaultSpeed,
                startNode,
                endNode
        )

        assert(street is Edge)
    }

    @Test
    fun `Street has all properties`(){
        val name = "Street McStreetface"
        val defaultTrafficRate = 1.0
        val defaultSpeed = 1.0
        val startNode = Crossing()
        val endNode = Crossing()

        val street = Street(
                name,
                defaultTrafficRate,
                defaultTrafficRate,
                defaultSpeed,
                defaultSpeed,
                startNode,
                endNode
        )

        assertNotNull(street.startNode)
        assertNotNull(street.endNode)
        assertNotNull(street.name)
        assertNotNull(street.currentTrafficRate)
        assertNotNull(street.defaultTrafficRate)
        assertNotNull(street.currentSpeed)
        assertNotNull(street.defaultSpeed)

        assertEquals(0, startNode.edges.size)
        assertEquals(0, endNode.edges.size)
    }

    @Test
    fun `Crossing initializeable and is Node`(){
        val crossing = Crossing()
        assert(crossing is Node)
    }

    @Test
    fun `Crossing has edges`(){
        val crossing = Crossing()

        assertNotNull(crossing.edges)
    }
}