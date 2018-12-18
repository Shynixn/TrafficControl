package at.jku.controlsystem.service

import at.jku.controlsystem.service.mock.TrafficApiServiceMock
import javax.enterprise.context.RequestScoped
import javax.inject.Inject

@RequestScoped
class TrafficApiFactory(@Inject val trafficApiService: TrafficApiServiceMock){
    fun getTrafficApiService(): TrafficApiService{
        return trafficApiService
    }
}