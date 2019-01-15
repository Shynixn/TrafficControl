package at.jku.controlsystem.scheduling

import at.jku.controlsystem.repository.IncidentRepository
import at.jku.controlsystem.service.RoadMaintenanceService
import java.util.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@ApplicationScoped
@Default
class RoadMaintenanceSchedulerImpl: RoadMaintenanceScheduler{
    private var logger: Logger = Logger.getLogger(this::class.qualifiedName)

    @Inject
    private lateinit var roadMaintenanceService: RoadMaintenanceService

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
                    Thread.sleep(1000 * 60 * 10)
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
        logger.info("Polling from Road Maintenance")

        IncidentRepository.instance.incidents = roadMaintenanceService.getIncidents()

    }
}