package at.jku.controlsystem.parser

import at.jku.controlsystem.contract.Crossing
import at.jku.controlsystem.contract.Street
import javax.json.Json
import javax.json.JsonNumber
import javax.json.JsonObject
import javax.json.JsonValue

class CityParser{
    private val idStreetMap = HashMap<Int, Street>()
    private val idCrossingMap = HashMap<Int, Crossing>()
    private val streetIdMap = HashMap<Street, Int>()
    private val crossingIdMap = HashMap<Crossing, Int>()
    private var currentId = 0

    fun toJson(rootEdge: Street): JsonObject{
        initParser()

        return parseEdge(rootEdge)
    }

    private fun parseEdge(rootEdge: Street): JsonObject {
        val jsonObjectBuilder = Json.createObjectBuilder()

        streetIdMap.put(rootEdge, currentId)

        jsonObjectBuilder
                .add("id", currentId++)
                .add("name", rootEdge.name)
                .add("currentTrafficRate", rootEdge.currentTrafficRate)
                .add("currentSpeed", rootEdge.currentSpeed)
                .add("defaultSpeed", rootEdge.defaultSpeed)
                .add("defaultTrafficRate", rootEdge.defaultTrafficRate)

        if (crossingIdMap.containsKey(rootEdge.startNode)) {
            jsonObjectBuilder.add("startNode", crossingIdMap[rootEdge.startNode]!!)
        } else {
            jsonObjectBuilder.add("startNode", parseNode(rootEdge.startNode as Crossing))
        }

        if (rootEdge.endNode != null) {
            if (crossingIdMap.containsKey(rootEdge.endNode)) {
                jsonObjectBuilder.add("endNode", crossingIdMap[rootEdge.endNode]!!)
            } else {
                jsonObjectBuilder.add("endNode", parseNode(rootEdge.endNode!! as Crossing))
            }
        }

        return jsonObjectBuilder.build()
    }

    private fun parseNode(node: Crossing): JsonObject {
        val jsonObjectBuilder = Json.createObjectBuilder()
        val jsonArrayBuilder = Json.createArrayBuilder()

        crossingIdMap.put(node, currentId)

        jsonObjectBuilder.add("id", currentId++)

        for (edge in node.edges){
            if(streetIdMap.containsKey(edge)){
                jsonArrayBuilder.add(streetIdMap[edge]!!)
            }else{
                jsonArrayBuilder.add(parseEdge(edge as Street))
            }
        }

        jsonObjectBuilder.add("edges", jsonArrayBuilder.build())

        return jsonObjectBuilder.build()
    }

    fun fromJson(response: JsonObject): Street {
        initParser()

        return if(response.getString("name") != null){
            readEdge(response)
        }else{
            readNode(response).edges[0] as Street
        }
    }

    private fun readNode(response: JsonObject): Crossing {
        val id = response.getInt("id")
        val edges = response.getJsonArray("edges")
        val crossing = Crossing()

        idCrossingMap[id] = crossing

        for (obj in edges){
            if(obj.valueType == JsonValue.ValueType.NUMBER){
                val streetId = obj as JsonNumber
                crossing.edges.add(idStreetMap[streetId.intValue()]!!)
            }else{
                val street = obj as JsonObject
                crossing.edges.add(readEdge(street))
            }
        }

        return crossing
    }

    private fun readEdge(response: JsonObject): Street {
        val id = response.getInt("id")
        val name = response.getString("name")
        val currentSpeed = response.getJsonNumber("currentSpeed").doubleValue()
        val currentTrafficRate = response.getJsonNumber("currentTrafficRate").doubleValue()
        val defaultSpeed = response.getJsonNumber("defaultSpeed").doubleValue()
        val defaultTrafficRate = response.getJsonNumber("defaultTrafficRate").doubleValue()

        val street = Street(
                name,
                currentTrafficRate,
                defaultTrafficRate,
                currentSpeed,
                defaultSpeed,
                null,
                null
        )

        idStreetMap[id] = street

        val startNode =
            if(response.getInt("startNode", -1) == -1){
                readNode(response.getJsonObject("startNode"))
            }else{
                idCrossingMap[response.getInt("startNode")]
            }

        val endNode =
                if(response.getInt("endNode", -1) == -1){
                    readNode(response.getJsonObject("endNode"))
                }else{
                    idCrossingMap[response.getInt("endNode")]
                }

        street.startNode = startNode
        street.endNode = endNode

        return street
    }

    private fun initParser() {
        streetIdMap.clear()
        crossingIdMap.clear()
        idStreetMap.clear()
        idCrossingMap.clear()
        currentId = 0
    }
}