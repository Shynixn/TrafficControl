package at.jku.controlsystem.contract

import at.jku.controlsystem.Produce
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class ContractTests{


    @Test
    fun `Street initializeable and is Edge`(){
        val street = Produce().defaultStreet()

        assert(street is Edge)
    }

    @Test
    fun `Street has all properties`(){
        val street = Produce().defaultStreet()

        assertNotNull(street.startNode)
        assertNotNull(street.endNode)
        assertNotNull(street.name)
        assertNotNull(street.currentTrafficRate)
        assertNotNull(street.defaultTrafficRate)
        assertNotNull(street.currentSpeed)
        assertNotNull(street.defaultSpeed)

        assertEquals(0, street.startNode!!.edges.size)
        assertEquals(0, street.endNode!!.edges.size)
    }

    @Test
    fun `Same street is equal`(){
        val street1 = Produce().defaultStreet()
        val street2 = Produce().defaultStreet()

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