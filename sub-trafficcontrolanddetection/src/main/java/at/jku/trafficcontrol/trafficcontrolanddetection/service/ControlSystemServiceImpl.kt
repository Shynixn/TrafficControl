package at.jku.trafficcontrol.trafficcontrolanddetection.service

import at.jku.trafficcontrol.trafficcontrolanddetection.TrafficControlAndDetectionApplication
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.ClientService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.ControlSystemService
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.RequestHelpInformation
import at.jku.trafficcontrol.trafficcontrolanddetection.extension.sync
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.Files
import java.util.concurrent.CompletableFuture
import javax.inject.Inject
import javax.ws.rs.client.Entity

/**
 * Service to talk with the controlSystem.
 */
class ControlSystemServiceImpl @Inject constructor(private val clientService : ClientService) : ControlSystemService {
    /**
     *  Requests help from the control system.
     */
    override fun requestHelp(): CompletableFuture<Void?> {
        val future = CompletableFuture<Void?>()

        sync {
            withContext(Dispatchers.IO) {
                val dataSource = Files.readAllLines(File(Thread.currentThread().contextClassLoader.getResource("request-information.txt").toURI()).toPath()).joinToString()

                clientService.createClient()
                        .target(TrafficControlAndDetectionApplication.CONTROL_SYSTEM_URL + "/requesthelp")
                        .request().post(Entity.json(RequestHelpInformation(dataSource)))
            }

            future.complete(null)
        }

        return future
    }
}