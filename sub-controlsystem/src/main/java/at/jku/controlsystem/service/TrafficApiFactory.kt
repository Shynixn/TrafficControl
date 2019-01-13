package at.jku.controlsystem.service

import javax.enterprise.context.RequestScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@RequestScoped
@Default
open class TrafficApiFactory{
    constructor(){

    }

    constructor(trafficApiService: TrafficApiService){
        this.trafficApiService = trafficApiService
    }

    @Inject
    private lateinit var trafficApiService: TrafficApiService

    open fun getTrafficApiService(): TrafficApiService{
        return trafficApiService;
    }

    open fun setTrafficApiSerivce(trafficApiService: TrafficApiService) {
        this.trafficApiService = trafficApiService
    }
}