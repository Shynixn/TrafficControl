package at.jku.trafficcontrol.trafficcontrolanddetection.service

import at.jku.trafficcontrol.trafficcontrolanddetection.TrafficControlAndDetectionApplication
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.ClientService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.ControlSystemService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.LoggingService
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.RequestHelpInformation
import at.jku.trafficcontrol.trafficcontrolanddetection.extension.sync
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.Files
import java.util.concurrent.CompletableFuture
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.ws.rs.client.Entity
import javax.ws.rs.core.Response

/**
 * Service to talk with the controlSystem.
 */
@Default
@ApplicationScoped
class ControlSystemServiceImpl @Inject constructor(private val clientService: ClientService, private val logger: LoggingService) : ControlSystemService {
    /**
     *  Requests help from the control system.
     */
    override fun requestHelp(): CompletableFuture<Void?> {
        val future = CompletableFuture<Void?>()

        sync {
            withContext(Dispatchers.IO) {
                try {
                    val dataSource = Files.readAllLines(File(Thread.currentThread().contextClassLoader.getResource("request-information.txt").toURI()).toPath()).joinToString()

                    val response = clientService.createClient()
                            .target(TrafficControlAndDetectionApplication.CONTROL_SYSTEM_URL + "/requesthelp")
                            .request().post(Entity.json(RequestHelpInformation(dataSource)))

                    if (response.statusInfo != Response.Status.OK) {
                        logger.error("Received statuscode ${response.status} from ${response.location} with message ${response.entity}.")
                    } else {
                        logger.info("Successfully sent data to ${response.location}.")
                    }
                } catch (e: Exception) {
                    logger.error("Failed to request help.", e)
                }
            }

            future.complete(null)
        }

        return future
    }
}