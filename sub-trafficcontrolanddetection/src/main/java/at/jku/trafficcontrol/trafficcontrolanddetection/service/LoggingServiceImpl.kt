package at.jku.trafficcontrol.trafficcontrolanddetection.service

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.LoggingService
import java.util.logging.Level
import java.util.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default

/**
 * Logging.
 */
@Default
@ApplicationScoped
class LoggingServiceImpl : LoggingService {
    private val logger = Logger.getLogger("TRAFFIC_CONTROL_AND_DETECTION")
    /**
     * Sends a info log.
     */
    override fun info(text: String) {
        logger.log(Level.INFO, text)
    }

    /**
     * Reports an error log.
     */
    override fun error(text: String, error: Throwable?) {
        if (error == null) {
            logger.log(Level.WARNING, text)
        } else {
            logger.log(Level.WARNING, text)
        }
    }
}