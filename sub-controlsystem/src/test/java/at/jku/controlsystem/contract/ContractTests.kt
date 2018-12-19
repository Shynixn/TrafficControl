package at.jku.controlsystem.contract

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class ContractTests{
    fun produceDefaultStreet(): Street{
        val name = "Street McStreetface"
        val defaultTrafficRate = 1.0
        val defaultSpeed = 1.0
        val startNode = Crossing()
        val endNode = Crossing()

        return Street(
                name,
                defaultTrafficRate,
                defaultTrafficRate,
                defaultSpeed,
                defaultSpeed,
                startNode,
                endNode
        )
    }

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
    fun `Same street is equal`(){
        val street1 = produceDefaultStreet()
        val street2 = produceDefaultStreet()

        street2.name = "Streety McStreetface 2"

        assertEquals(street1, street1)
        assertNotEquals(street1, street2)
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