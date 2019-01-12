package at.jku.controlsystem.service

import javax.enterprise.context.RequestScoped
import javax.enterprise.inject.Default
import javax.json.JsonObject
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.MediaType

/**
 * Api to communicate with Traffic Control and Detection
 */
@RequestScoped
@Default
class TrafficApiServiceImpl : TrafficApiService{

    /**
     * Retrieves current traffic information from
     * Traffic Control and Detection subsystem
     */
    override fun getCurrentTraffic(): JsonObject {
        val client = ClientBuilder.newClient()
        val cityObject =
                client.target("http://localhost:8082/trafficcontrolanddetection/city")
                .request(MediaType.APPLICATION_JSON)
                .get(JsonObject::class.java)

        return cityObject
    }

}