package at.jku.controlsystem.service.mock

import at.jku.controlsystem.contract.Crossing
import at.jku.controlsystem.contract.Street
import at.jku.controlsystem.parser.CityParser
import at.jku.controlsystem.service.TrafficApiService
import javax.enterprise.context.RequestScoped
import javax.enterprise.inject.Default
import javax.json.JsonObject

@RequestScoped
@Default
class TrafficApiServiceMock: TrafficApiService{
    companion object {
        lateinit var cityMock: JsonObject
    }

    init {
        val nodes = mutableListOf(
                Crossing(),
                Crossing(),
                Crossing(),
                Crossing()
        )
        for(i in 0 until nodes.size-1){
            val edge1 = Street(
                    "Street ${i}.1",
                    1.0,
                    1.0,
                    1.0,
                    1.0,
                    nodes[i],
                    nodes[i+1]
            )
            val edge2 = Street(
                    "Street ${i}.2",
                    1.0,
                    1.0,
                    1.0,
                    1.0,
                    nodes[i+1],
                    nodes[i]
            )
            nodes[i].edges.add(edge1)
            nodes[i].edges.add(edge2)
            nodes[i+1].edges.add(edge1)
            nodes[i+1].edges.add(edge2)
        }

        cityMock = CityParser().toJson(nodes[0].edges[0] as Street)
    }

    override fun getCurrentTraffic(): JsonObject {
        return cityMock
    }

}