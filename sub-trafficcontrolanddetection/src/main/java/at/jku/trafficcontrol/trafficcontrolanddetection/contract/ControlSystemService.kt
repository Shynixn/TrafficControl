package at.jku.trafficcontrol.trafficcontrolanddetection.contract

import java.util.concurrent.CompletableFuture

/**
 * Service to talk with the controlSystem.
 */
interface ControlSystemService {
    /**
     *  Requests help from the control system.
     */
    fun requestHelp(): CompletableFuture<Void?>
}