package at.jku.trafficcontrol.trafficcontrolanddetection.contract

import at.jku.trafficcontrol.trafficcontrolanddetection.entity.City

/**
 * Manages the city.
 */
interface CityService {
    /**
     * Gets the currently managed city by the city service.
     */
    fun getMainCity() : City
}