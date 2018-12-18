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
import java.util.*
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
                val dataSource = Files.readAllBytes(File(Thread.currentThread().contextClassLoader.getResource("sad-cat.jpg").toURI()).toPath())
                val encodedRequestHelp = Base64.getEncoder().encodeToString(dataSource)

                clientService.createClient()
                        .target(TrafficControlAndDetectionApplication.CONTROL_SYSTEM_URL + "/requesthelp")
                        .request().post(Entity.json(RequestHelpInformation(encodedRequestHelp)))
            }

            future.complete(null)
        }

        return future
    }
}