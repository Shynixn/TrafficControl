package at.jku.trafficcontrol.trafficcontrolanddetection.controller

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.CityService
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * Offers the city meta data as interface to other subsystems.
 */
@Path("/city")
open class CityController() {
    private lateinit var cityService: CityService

    /**
     * Creates a new instance of [CityController] with [CityService] as dependency.
     */
    @Inject
    constructor(cityService: CityService) : this() {
        this.cityService = cityService
    }

    /**
     * Gets the main city.
     * @return 200 HTTP OK on success. City can never be null.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    open fun getCity(): Response {
        val city = cityService.getMainCity()
        return Response.ok(city).build()
    }
}