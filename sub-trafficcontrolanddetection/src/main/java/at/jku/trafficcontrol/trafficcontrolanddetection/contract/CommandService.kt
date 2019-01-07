package at.jku.trafficcontrol.trafficcontrolanddetection.contract

import at.jku.trafficcontrol.trafficcontrolanddetection.entity.Command

interface CommandService {
    /**
     * Applies a new command with control system authority.
     */
    fun applyControlSystemCommand(command: Command)

    /**
     * Applies a new command with traffic supervisor authority.
     */
    fun applySupervisorCommand(command: Command)
}