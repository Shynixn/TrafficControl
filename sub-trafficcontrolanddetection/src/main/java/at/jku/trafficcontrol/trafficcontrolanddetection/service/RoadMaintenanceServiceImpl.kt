package at.jku.trafficcontrol.trafficcontrolanddetection.service

import at.jku.trafficcontrol.trafficcontrolanddetection.TrafficControlAndDetectionApplication
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.ClientService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.RoadMaintenanceService
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.Street
import at.jku.trafficcontrol.trafficcontrolanddetection.extension.sync
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import java.util.concurrent.CompletableFuture
import javax.inject.Inject
import javax.ws.rs.client.Entity
import javax.ws.rs.core.Response

/**
 * Road Maintenance.
 */
class RoadMaintenanceServiceImpl @Inject constructor(private val clientService: ClientService, private val logger: Logger) : RoadMaintenanceService {
    /**
     * Reports the amount of cars driven on street since the last report.
     */
    override fun reportAmountOfCars(streets: List<Street>): CompletableFuture<Void?> {
        val future = CompletableFuture<Void?>()

        sync {
            withContext(Dispatchers.IO) {
                val response = clientService.createClient()
                        .target(TrafficControlAndDetectionApplication.ROADMAINTENANCE_SYSTEM_URL + "/carstatistics")
                        .request().post(Entity.json(streets))

                if (response.statusInfo != Response.Status.OK) {
                    logger.error("Received statuscode ${response.status} from ${response.location} with message ${response.entity}.")
                } else {
                    logger.info("Successfully sent data to ${response.location}.")
                }
            }

            future.complete(null)
        }

        return future
    }

    /**
     * Reports the blocked streets to the traffic control and detection.
     */
    override fun reportBlockStreets(streets: List<Street>): CompletableFuture<Void?> {
        val future = CompletableFuture<Void?>()

        sync {
            withContext(Dispatchers.IO) {
                val response = clientService.createClient()
                        .target(TrafficControlAndDetectionApplication.ROADMAINTENANCE_SYSTEM_URL + "/blockstreets")
                        .request().post(Entity.json(streets))

                if (response.statusInfo != Response.Status.OK) {
                    logger.error("Received statuscode ${response.status} from ${response.location} with message ${response.entity}.")
                } else {
                    logger.info("Successfully sent data to ${response.location}.")
                }
            }

            future.complete(null)
        }

        return future
    }
}