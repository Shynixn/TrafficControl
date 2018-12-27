package at.jku.trafficcontrol.trafficcontrolanddetection.contract

import at.jku.trafficcontrol.trafficcontrolanddetection.entity.Street
import java.util.concurrent.CompletableFuture

/**
 * Road Maintenance.
 */
interface RoadMaintenanceService {
    /**
     * Reports the amount of cars driven on street since the last report.
     */
    fun reportAmountOfCars(streets: List<Street>): CompletableFuture<Void?>

    /**
     * Reports the blocked streets to the traffic control and detection.
     */
    fun reportBlockStreets(streets: List<Street>): CompletableFuture<Void?>
}