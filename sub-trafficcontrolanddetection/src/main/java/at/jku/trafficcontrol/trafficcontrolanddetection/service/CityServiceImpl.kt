package at.jku.trafficcontrol.trafficcontrolanddetection.service

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.CityService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.LoggingService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.TrafficParticipantsService
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

/**
 * City service.
 */
@Default
@ApplicationScoped
class CityServiceImpl @Inject constructor(private val trafficParticipantsService: TrafficParticipantsService, private val logger: LoggingService) : CityService {
    private var city: City = City("Linz", "City in Austria.")

    /**
     * Initializes the city.
     */
    init {
        val node1 = Node(1)
        val node2 = Node(2)
        val node3 = Node(3)

        val edge1 = Street(1, node1, node2)
        val edge2 = Street(2, node2, node3)

        edge1.enterTrafficLight = TrafficLight()
        edge2.enterTrafficLight = TrafficLight()

        city.edges.add(edge1)
        city.edges.add(edge2)

        city.nodes.add(node1)
        city.nodes.add(node2)
        city.nodes.add(node3)
    }

    /**
     * Gets the currently managed city by the city service.
     */
    override fun getMainCity(): City {
        return city
    }

    /**
     * Refreshes the state of the city with the latest collected information of traffic participants.
     */
    override fun refreshCityTrafficParticipants(): CompletableFuture<Void?> {
        val future = CompletableFuture<Void?>()

        GlobalScope.launch(Dispatchers.Default) {
            logger.info("Updating city...")

            val inductionLoopTask = async(Dispatchers.IO) {
                trafficParticipantsService.requestInductionLoopsOfCity()
            }

            val camerasTask = async(Dispatchers.IO) {
                trafficParticipantsService.requestCamerasOfCity()
            }

            val trafficInformation = ArrayList<TrafficInformation>()
            trafficInformation.addAll(inductionLoopTask.await().get())
            trafficInformation.addAll(camerasTask.await().get())

            updateCity(trafficInformation)
            logger.info("Finished updating city.")

            future.complete(null)
        }

        return future
    }

    /**
     * Updates the city with the given [information].
     */
    private fun updateCity(information: List<TrafficInformation>) {
        if (information.isEmpty()) {
            logger.info("No new traffic information available.")
            return
        }

        for (trafficInformation in information) {
            val edge = city.edges.filter { e -> e is Street }.singleOrNull { e -> e.id == trafficInformation.streetId } as Street?

            if (edge == null) {
                logger.info("TrafficInformation contains an unknown edge ${trafficInformation.streetId}.")
                continue
            }

            edge.amountOfCars = trafficInformation.amountOfCars
            logger.info("Street ${trafficInformation.streetId} contains now ${edge.amountOfCars} cars.")
        }
    }
}