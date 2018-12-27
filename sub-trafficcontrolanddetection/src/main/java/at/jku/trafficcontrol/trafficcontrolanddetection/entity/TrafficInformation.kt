package at.jku.trafficcontrol.trafficcontrolanddetection.entity

/**
 * Traffic information.
 */
class TrafficInformation(
        /**
         * Id of the street.
         */
        var streetId: Int,
        /**
         * Amount of cars on this street.
         */
        var amountOfCars: Int) {
}