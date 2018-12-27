package at.jku.trafficcontrol.trafficcontrolanddetection.mock

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.CityService
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.City
import java.util.concurrent.CompletableFuture
import javax.inject.Inject

/**
 * Mocked service.
 */
open class MockedCityService @Inject constructor() : CityService {
    /**
     * Refreshes the state of the city with the latest collected information of traffic participants.
     */
    override fun refreshCityTrafficParticipants(): CompletableFuture<Void?> {
        return CompletableFuture()
    }

    /**
     * Gets the currently managed city by the city service.
     */
    override fun getMainCity(): City {
        return City("MockedTown", "Mocked City in Austria")
    }
}