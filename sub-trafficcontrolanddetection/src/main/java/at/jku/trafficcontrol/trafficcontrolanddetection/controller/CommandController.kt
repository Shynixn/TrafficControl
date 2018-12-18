package at.jku.trafficcontrol.trafficcontrolanddetection.controller

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.CommandService
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.Command
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * Offers command controlling as interface to other subsystems.
 */
@Path("/command")
open class CommandController() {
    private lateinit var commandService: CommandService

    /**
     * Creates a new instance of [CommandController] with [CommandService] as dependency.
     */
    @Inject
    constructor(commandService: CommandService) : this() {
        this.commandService = commandService
    }

    /**
     * Post endpoint to accept commands.
     */
    @POST
    @Path("{authority}")
    @Consumes(MediaType.APPLICATION_JSON)
    open fun postSystemCommand(@PathParam("authority") authority: String, command: Command): Response {
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