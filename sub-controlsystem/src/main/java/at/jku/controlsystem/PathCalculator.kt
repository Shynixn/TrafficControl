package at.jku.controlsystem

import at.jku.controlsystem.service.TrafficApiFactory
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@Default
@ApplicationScoped
class PathCalculator: Runnable{
    @Inject
    private lateinit var trafficApiServiceFactory: TrafficApiFactory

    private val trafficApiService
        get() = trafficApiServiceFactory.trafficApiService

    override fun run() {
        //Calculate best path
    }

}