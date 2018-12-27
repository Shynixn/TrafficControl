package at.jku.trafficcontrol.trafficcontrolanddetection.contract

import at.jku.trafficcontrol.trafficcontrolanddetection.entity.City
import java.util.concurrent.CompletableFuture

/**
 * Manages the city.
 */
interface CityService {
    /**
     * Gets the currently managed city by the city service.
     */
    fun getMainCity(): City

    /**
     * Refreshes the state of the city with the latest collected information of traffic participants.
     */
    fun refreshCityTrafficParticipants(): CompletableFuture<Void?>
}