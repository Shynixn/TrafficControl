package at.jku.controlsystem.parser

import at.jku.controlsystem.Produce
import at.jku.controlsystem.contract.Crossing
import at.jku.controlsystem.contract.Street
import org.junit.jupiter.api.Test
import javax.json.JsonObject
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class CityParserTests{
    @Test
    fun `Parse edge only`(){
        val street = Produce().defaultStreet()
        street.startNode = null
        street.endNode = null

        val jsonObject = CityParser().toJson(street)

        assertNotNull(jsonObject)
        assertJsonIsStreet(jsonObject, street)
    }

    @Test
    fun `Parse with crossings`(){
        val street = Produce().defaultStreet()
        val jsonObject = CityParser().toJson(street)

        assertNotNull(jsonObject)
        assertJsonIsStreet(jsonObject, street)
    }

    @Test
    fun `Parse with two streets and two crossings`(){
        val street = Produce().defaultStreet()
        val crossing1 = street.startNode as Crossing
        val crossing2 = street.endNode as Crossing
        val street2 = Produce().defaultStreet()

        street2.startNode = crossing2
        street2.endNode = crossing1

        val jsonObject = CityParser().toJson(street)
        val parsedStreet = CityParser().fromJson(jsonObject)

        assertJsonIsStreet(jsonObject, street)
        assertEquals(street.hashCode(), parsedStreet.hashCode())
        assertEquals(street.startNode, parsedStreet.startNode)
        assertEquals(street.endNode, parsedStreet.endNode)
        assertEquals(street2.endNode, parsedStreet.startNode)
        assertEquals(street2.startNode, parsedStreet.endNode)
    }

    @Test
    fun `Parse crossing as root object`(){
        val parsedStreet = CityParser().toJson(Produce().defaultStreet())
        val nodeObject = parsedStreet.getJsonObject("startNode")

        assertFailsWith(ResponseIsCrossingException::class) { CityParser().fromJson(nodeObject) }
    }

    private fun assertJsonIsStreet(jsonObject: JsonObject, street: Street) {
        val name = jsonObject.getString("name")
        val currentTrafficRate = jsonObject.getJsonNumber("currentTrafficRate").doubleValue()
        val defaultTrafficRate = jsonObject.getJsonNumber("defaultTrafficRate").doubleValue()
        val currentSpeed = jsonObject.getJsonNumber("currentSpeed").doubleValue()
        val defaultSpeed = jsonObject.getJsonNumber("defaultSpeed").doubleValue()
        val startNode = jsonObject["startNode"]
        val endNode = jsonObject["endNode"]

        assertEquals(street.name, name)
        assertEquals(street.currentTrafficRate, currentTrafficRate)
        assertEquals(street.defaultTrafficRate, defaultTrafficRate)
        assertEquals(street.currentSpeed, currentSpeed)
        assertEquals(street.defaultSpeed, defaultSpeed)

        if(street.startNode == null){
            assertNull(startNode)
        } else {
            assertNotNull(startNode)
        }
        if(street.endNode == null){
            assertNull(endNode)
        }else{
            assertNotNull(endNode)
        }
    }
}