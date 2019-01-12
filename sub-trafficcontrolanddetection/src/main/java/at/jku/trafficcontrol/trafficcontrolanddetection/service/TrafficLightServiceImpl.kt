package at.jku.trafficcontrol.trafficcontrolanddetection.service

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.CityService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.TrafficLightService
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.Street
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.TrafficLightState
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

/**
 * Traffic light.
 */
@Default
@ApplicationScoped
class TrafficLightServiceImpl @Inject constructor(private val cityService: CityService) : TrafficLightService {
    private val fixedYellowSeconds = 5

    /**
     * Update the traffic lights of the city.
     */
    override fun updateTrafficLights() {
        val city = cityService.getMainCity()
        val currentTime = Date().time

        for (edge in city.edges.filter { e -> e is Street && e.enterTrafficLight != null }) {
            val street = edge as Street
            val trafficLight = street.enterTrafficLight!!
            val difference = (currentTime - trafficLight.lastSwitch) / 1000

            if (trafficLight.state == TrafficLightState.GREEN) {
                if (difference >= trafficLight.greenSeconds) {
                    trafficLight.state = TrafficLightState.YELLOW
                    trafficLight.toGreen = false
                }
            } else if (trafficLight.state == TrafficLightState.RED) {
                if (difference >= trafficLight.redSeconds) {
                    trafficLight.state = TrafficLightState.YELLOW
                    trafficLight.toGreen = true
                }
            } else if (trafficLight.state == TrafficLightState.YELLOW) {
                if (difference >= fixedYellowSeconds) {
                    if (trafficLight.toGreen) {
                        trafficLight.state = TrafficLightState.GREEN
                    } else {
                        trafficLight.state = TrafficLightState.RED
                    }
                }
            }
        }
    }
}