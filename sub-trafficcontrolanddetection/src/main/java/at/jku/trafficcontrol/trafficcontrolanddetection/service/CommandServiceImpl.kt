package at.jku.trafficcontrol.trafficcontrolanddetection.service

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.CityService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.CommandService
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.City
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.Command
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

/**
 * Command Service.
 */
@Default
@ApplicationScoped
class CommandServiceImpl @Inject constructor(private val cityService: CityService) : CommandService {
    private val outDatedDifference = 1000 * 60

    /**
     * Applies a new command with control system authority.
     */
    override fun applyControlSystemCommand(command: Command) {
        val city = cityService.getMainCity()
        addCommand(city, command)
    }

    /**
     * Applies a new command with traffic supervisor authority.
     */
    override fun applySupervisorCommand(command: Command) {
        val city = cityService.getMainCity()
        command.highestAuthority = true
        addCommand(city, command)
    }

    /**
     * Adds a new command to the given [items] and removes outdated onces.
     */
    private fun addCommand(city: City, newCommand: Command) {
        val currentDate = Date()

        for (oldCommand in city.customCommands.toTypedArray()) {
            val difference = currentDate.time - oldCommand.executionDate.time

            if (difference >= outDatedDifference) {
                city.customCommands.remove(oldCommand)
                continue
            }

            if (oldCommand.type == newCommand.type && newCommand.highestAuthority) {
                city.customCommands.remove(oldCommand)
                continue
            }
        }

        city.customCommands.add(newCommand)
    }
}