package at.jku.controlsystem.service

import java.io.StringReader
import java.net.ConnectException
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.json.Json
import javax.json.JsonObject
import javax.ws.rs.ProcessingException
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.MediaType

/**
 * Api to communicate with Traffic Control and Detection
 */
@ApplicationScoped
@Default
class TrafficApiServiceImpl : TrafficApiService{

    private val baseUrl = "http://localhost:8082/trafficcontrolanddetection/city"
    private val username = System.getenv("TRAFFIC_CONTROL_USERNAME")
    private val password = System.getenv("TRAFFIC_CONTROL_PASSWORD")

    /**
     * Retrieves current traffic information from
     * Traffic Control and Detection subsystem
     */
    override fun getCurrentTraffic(): JsonObject {
        try {
            val client = ClientBuilder.newClient()
            val authorizationHeader = "Basic " + Base64.getEncoder().encodeToString(
                    "$username:$password".toByteArray()
            )

            val response =
                    client.target(baseUrl)
                            .request(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .header("Authorization", authorizationHeader)
                            .get() ?: return ResponseError().noResponse

            val jsonString = response.readEntity(String::class.java)

            return Json.createReader(StringReader(jsonString)).readObject()
        }catch (e: ConnectException){
            return ResponseError().noResponse
        }catch (e: ProcessingException){
            if(e.cause is ConnectException){
                return ResponseError().noResponse
            }else {
                throw e
            }
        }
    }

}