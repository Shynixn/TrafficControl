package at.jku.controlsystem.service

import at.jku.controlsystem.service.mock.TrafficApiServiceMock
import javax.enterprise.context.RequestScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@RequestScoped
@Default
class TrafficApiFactory{
    @Inject
    lateinit var trafficApiService: TrafficApiServiceMock
}