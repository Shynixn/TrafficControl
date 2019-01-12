package at.jku.trafficcontrol.trafficcontrolanddetection.contract

/**
 * Logging.
 */
interface LoggingService {

    /**
     * Sends a info log.
     */
    fun info(text: String)

    /**
     * Reports an error log.
     */
    fun error(text: String, error: Throwable? = null)
}