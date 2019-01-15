package at.jku.controlsystem.service

import at.jku.controlsystem.entity.IncidentResponse
import java.io.StringReader
import java.net.ConnectException
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.json.Json
import javax.json.JsonValue
import javax.ws.rs.ProcessingException
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.MediaType

@ApplicationScoped
@Default
class RoadMaintenanceServiceImpl: RoadMaintenanceService{
    val baseUrl = "http://localhost:8083/roadmaintenance/incident"
    private val username = System.getenv("TRAFFIC_CONTROL_USERNAME")
    private val password = System.getenv("TRAFFIC_CONTROL_PASSWORD")

    override fun getIncidents(): List<IncidentResponse> {
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
                            .get() ?: return emptyList()


            val responseString = response.readEntity(String::class.java)
            val incidentJsonArray = Json.createReader(StringReader(responseString)).readArray()
            val result = mutableListOf<IncidentResponse>()

            for (i in 0 until incidentJsonArray.size){
                val incidentResponse = IncidentResponse()
                val incident = incidentJsonArray.getJsonObject(i)
                val assignee = incident.getJsonObject("assignee")
                val beginDate = incident.get("begin")
                val endDate = incident.get("end")

                incidentResponse.id = incident.getInt("id", -1)
                incidentResponse.status = incident.getString("status", "")
                incidentResponse.type = incident.getString("type", "")
                incidentResponse.priority = incident.getString("priority", "")

                if(assignee != null){
                    incidentResponse.assigned = assignee.getString("name", "")
                }
                if(beginDate != null && beginDate.valueType == JsonValue.ValueType.OBJECT){
                    val date = incident.getJsonObject("begin")
                    incidentResponse.startDay = date.getInt("dayOfMonth", 0)
                    incidentResponse.startMonth = date.getInt("monthValue", 0)
                    incidentResponse.startYear = date.getInt("year", 0)
                }
                if(endDate != null && endDate.valueType == JsonValue.ValueType.OBJECT){
                    val date = incident.getJsonObject("end")
                    incidentResponse.endDay = date.getInt("dayOfMonth", 0)
                    incidentResponse.endMonth = date.getInt("monthValue", 0)
                    incidentResponse.endYear = date.getInt("year", 0)
                }

                result.add(incidentResponse)
            }

            return result
        }catch (e: ConnectException){

        }catch (e: ProcessingException){
            if(e.cause !is ConnectException){
                throw e
            }
        }
        return emptyList()
    }

}