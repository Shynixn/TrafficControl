package at.jku.controlsystem.scheduling

import at.jku.controlsystem.repository.TrafficRepository
import at.jku.controlsystem.service.TrafficApiService
import java.util.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@Default
@ApplicationScoped
class TrafficApiSchedulerImpl : TrafficApiScheduler{
    private var logger: Logger = Logger.getLogger(this::class.qualifiedName)

    @Inject
    private lateinit var trafficApiService: TrafficApiService

    /**
     * Schedules the task every 1 second.
     */
    init {
        val thread = Thread(Runnable {
            var run = true
            while (run) {
                try {
                    schedule()
                    Thread.sleep(5000)
                } catch (e: Exception) {
                    logger.severe("Failed to schedule task.")
                    e.printStackTrace()
                    Thread.sleep(5000)
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
        logger.info("--------------------------")
        logger.info("Polling from Traffic Control and Detection")

        TrafficRepository.instance.currentTraffic = (trafficApiService.getCurrentTraffic())

    }
}