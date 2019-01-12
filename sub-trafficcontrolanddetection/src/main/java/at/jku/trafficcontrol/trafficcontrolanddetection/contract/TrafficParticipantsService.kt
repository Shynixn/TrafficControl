package at.jku.trafficcontrol.trafficcontrolanddetection.contract

import at.jku.trafficcontrol.trafficcontrolanddetection.entity.TrafficInformation
import java.util.concurrent.CompletableFuture

/**
 * Traffic participants.
 */
interface TrafficParticipantsService {
    /**
     * Requests all induction loops of cars on certain traffic lights.
     */
    fun requestInductionLoopsOfCity(): CompletableFuture<List<TrafficInformation>>

    /**
     * Requests all cameras of cars on a street.
     */
    fun requestCamerasOfCity(): CompletableFuture<List<TrafficInformation>>
}