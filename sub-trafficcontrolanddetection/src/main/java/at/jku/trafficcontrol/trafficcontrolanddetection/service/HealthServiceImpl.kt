package at.jku.trafficcontrol.trafficcontrolanddetection.service

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.CityService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.HealthService
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.HealthState
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.Street
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

/**
 * Basic health service.
 */
@Default
@ApplicationScoped
class HealthServiceImpl @Inject constructor(private val cityService: CityService) : HealthService {
    /**
     * Checks if the city is healthy.
     */
    override fun isCityHealth(): HealthState {
        val city = cityService.getMainCity()
        var healthState = HealthState.HEALTHY

        for (edge in city.edges) {
            val street = edge as Street

            if (street.amountOfCars >= street.maxCars) {
                healthState = HealthState.NOT_HEALTHY
                break
            }
        }

        return healthState
    }
}