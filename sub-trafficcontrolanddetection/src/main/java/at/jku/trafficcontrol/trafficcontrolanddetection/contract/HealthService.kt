package at.jku.trafficcontrol.trafficcontrolanddetection.contract

import at.jku.trafficcontrol.trafficcontrolanddetection.entity.HealthState

/**
 * Healthy service.
 */
interface HealthService {
    /**
     * Checks if the city is healthy.
     */
    fun isCityHealth(): HealthState
}