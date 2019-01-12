package at.jku.trafficcontrol.trafficcontrolanddetection.entity

/**
 * Traffic light.
 */
class TrafficLight(
        /**
         * State.
         */
        var state: TrafficLightState = TrafficLightState.GREEN, var greenSeconds: Int = 120, var redSeconds: Int = 70) {

    /**
     * SecondsCounter.
     */
    var lastSwitch: Long = Long.MAX_VALUE

    /**
     * Does the yellow light switch to green.
     */
    var toGreen: Boolean = false
}


