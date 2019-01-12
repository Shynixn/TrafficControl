@file:Suppress("UNCHECKED_CAST")

package at.jku.trafficcontrol.trafficcontrolanddetection.service

import at.jku.trafficcontrol.trafficcontrolanddetection.TrafficControlAndDetectionApplication
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.ClientService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.LoggingService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.TrafficParticipantsService
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.TrafficInformation
import at.jku.trafficcontrol.trafficcontrolanddetection.extension.sync
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.ws.rs.core.Response

/**
 * Traffic participants.
 */
@Default
@ApplicationScoped
class TrafficParticipantsServiceImpl @Inject constructor(private val clientService: ClientService, private val logger: LoggingService) : TrafficParticipantsService {
    /**
     * Requests all induction loops of cars on certain traffic lights.
     */
    override fun requestInductionLoopsOfCity(): CompletableFuture<List<TrafficInformation>> {
        val future = CompletableFuture<List<TrafficInformation>>()

        sync {
            val result = withContext(Dispatchers.IO) {
                try {
                    val response = clientService.createClient()
                            .target(TrafficControlAndDetectionApplication.TRAFFIC_PARTICIPANTS_SYSTEM_URL + "/inductionloops")
                            .request().get()

                    if (response.statusInfo != Response.Status.OK) {
                        logger.error("Received statuscode ${response.status} from ${response.location} with message ${response.entity}.")
                        ArrayList()
                    } else {
                        logger.info("Successfully received data from ${response.location}.")
                        response.entity as List<TrafficInformation>
                    }
                } catch (e: Exception) {
                    logger.error("Failed to request induction loops.", e)
                    ArrayList<TrafficInformation>()
                }
            }

            future.complete(result)
        }

        return future
    }

    /**
     * Requests all cameras of cars on a street.
     */
    override fun requestCamerasOfCity(): CompletableFuture<List<TrafficInformation>> {
        val future = CompletableFuture<List<TrafficInformation>>()

        sync {
            val result = withContext(Dispatchers.IO) {
                try {
                    val response = clientService.createClient()
                            .target(TrafficControlAndDetectionApplication.TRAFFIC_PARTICIPANTS_SYSTEM_URL + "/cameras")
                            .request().get()

                    if (response.statusInfo != Response.Status.OK) {
                        logger.error("Received statuscode ${response.status} from ${response.location} with message ${response.entity}.")
                        ArrayList()
                    } else {
                        logger.info("Successfully received data from ${response.location}.")
                        response.entity as List<TrafficInformation>
                    }
                } catch (e: Exception) {
                    logger.error("Failed to request cameras.", e)
                    ArrayList<TrafficInformation>()
                }
            }

            future.complete(result)
        }

        return future
    }
}