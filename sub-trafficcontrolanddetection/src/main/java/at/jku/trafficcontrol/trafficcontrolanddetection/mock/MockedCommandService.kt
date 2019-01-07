package at.jku.trafficcontrol.trafficcontrolanddetection.mock

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.CommandService
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.Command
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@Default
@ApplicationScoped
class MockedCommandService @Inject constructor(): CommandService {
    /**
     * Applies a new command with control system authority.
     */
    override fun applyControlSystemCommand(command: Command) {
    }

    /**
     * Applies a new command with traffic supervisor authority.
     */
    override fun applySupervisorCommand(command: Command) {
    }
}