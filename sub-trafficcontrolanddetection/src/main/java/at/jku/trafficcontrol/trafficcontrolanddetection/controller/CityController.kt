package at.jku.trafficcontrol.trafficcontrolanddetection.controller

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.AuthenticationService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.CityService
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.HeaderParam
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * Offers the city meta data as interface to other subsystems.
 */
@Path("/city")
@SecurityScheme(type = SecuritySchemeType.HTTP, scheme = "basic", name = "Authorization")
open class CityController() {
    private lateinit var cityService: CityService
    private lateinit var authenticationService: AuthenticationService

    /**
     * Creates a new instance of [CityController] with [CityService] and [AuthenticationService] as dependencies.
     */
    @Inject
    constructor(cityService: CityService, authenticationService: AuthenticationService) : this() {
        this.cityService = cityService
        this.authenticationService = authenticationService
    }

    /**
     * Gets the main city.
     * @return 200 HTTP OK on success. City can never be null.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @SecurityRequirement(name = "Authorization")
    open fun getCity(@HeaderParam("Authorization") authorizationHeader: String?): Response {
        if (!authenticationService.isAuthenticated(authorizationHeader)) {
            return Response.status(Response.Status.UNAUTHORIZED).build()
        }

        val city = cityService.getMainCity()
        return Response.ok(city).build()
    }
}