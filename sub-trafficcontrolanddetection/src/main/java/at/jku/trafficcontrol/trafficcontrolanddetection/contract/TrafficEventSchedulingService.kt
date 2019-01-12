package at.jku.trafficcontrol.trafficcontrolanddetection.contract

/**
 * Main program scheduler.
 */
interface TrafficEventSchedulingService {
    /**
     * Schedules a traffic event.
     */
    fun schedule()
}