package at.jku.trafficcontrol.trafficcontrolanddetection.service

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.*
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.HealthState
import kotlinx.coroutines.*
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

/**
 * Traffic event.
 */
@Default
@ApplicationScoped
class TrafficEventSchedulingServiceImpl @Inject constructor(private val cityService: CityService, private val trafficLightService: TrafficLightService, private val healthService: HealthService, private val controlSystemService: ControlSystemService, private val logger: LoggingService) : TrafficEventSchedulingService {

    /**
     * Schedules the task every 1 second.
     */
    init {
        val thread = Thread(Runnable {
            while (true) {
                try {
                    schedule()
                    Thread.sleep(5000)
                } catch (e: Exception) {
                    logger.error("Failed to schedule task.", e)
                }
            }
        })

        thread.isDaemon = true
        thread.start()
    }

    /**
     * Schedules a traffic event.
     */
    override fun schedule() {
        val uuid = UUID.randomUUID().toString()

        logger.info("--------------------------")
        logger.info("Start schedule... $uuid")

        // Get city data
        GlobalScope.launch(Dispatchers.Default) {
            val refreshCityTask = async(Dispatchers.IO) {
                cityService.refreshCityTrafficParticipants().get()
            }

            trafficLightService.updateTrafficLights()
            refreshCityTask.await()

            val cityHealthy = healthService.isCityHealth()

            if (cityHealthy == HealthState.NOT_HEALTHY) {
                withContext(Dispatchers.IO) {
                    controlSystemService.requestHelp().get()
                }
            }

            logger.info("Finished lazy scheduling $uuid.")
        }
    }
}