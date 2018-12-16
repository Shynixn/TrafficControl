package at.jku.trafficcontrol.trafficcontrolanddetection.mock

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.CityService
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.City
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@Default
@ApplicationScoped
open class MockedCityService @Inject constructor(): CityService {
    /**
     * Gets the currently managed city by the city service.
     */
    override fun getMainCity(): City {
        return City("MockedTown", "Mocked City in Austria")
    }
}