package at.jku.trafficcontrol.trafficcontrolanddetection.controller

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.AuthenticationService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.CommandService
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.Command
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * Offers command controlling as interface to other subsystems.
 */
@Path("/command")
@SecurityScheme(type = SecuritySchemeType.HTTP, scheme = "basic", name = "Authorization")
open class CommandController() {
    private lateinit var commandService: CommandService
    private lateinit var authenticationService: AuthenticationService

    /**
     * Creates a new instance of [CommandController] with [CommandService] and [AuthenticationService] as dependencies.
     */
    @Inject
    constructor(commandService: CommandService, authenticationService: AuthenticationService) : this() {
        this.commandService = commandService
        this.authenticationService = authenticationService
    }

    /**
     * Post endpoint to accept commands.
     */
    @POST
    @Path("{authority}")
    @Consumes(MediaType.APPLICATION_JSON)
    @SecurityRequirement(name = "Authorization")
    open fun postSystemCommand(@PathParam("authority") authority: String, @HeaderParam("Authorization") authorizationHeader: String?, command: Command): Response {
        if (!authenticationService.isAuthenticated(authorizationHeader)) {
            return Response.status(Response.Status.UNAUTHORIZED).build()
        }

        if (authority.equals("controlsystem", true)) {
            commandService.applyControlSystemCommand(command)
            return Response.ok().build()
        }

        if (authority.equals("trafficsupervisor", true)) {
            commandService.applySupervisorCommand(command)
            return Response.ok().build()
        }

        return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN)
                .entity("Only Authority 'controlsystem' and 'trafficsupervisor' are allowed.").build()
    }
}