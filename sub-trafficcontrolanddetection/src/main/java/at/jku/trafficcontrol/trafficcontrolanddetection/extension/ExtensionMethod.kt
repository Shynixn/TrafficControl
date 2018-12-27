package at.jku.trafficcontrol.trafficcontrolanddetection.extension

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.inject.spi.InjectionPoint
import javax.ws.rs.Produces

/**
 * Synchronizes the current context with the task.
 */
fun Any.sync(f: suspend () -> Unit) {
    GlobalScope.launch(Dispatchers.Default) {
        f.invoke()
    }
}

class LoggerProducer {
    /**
     * Produces a logger.
     */
    @Produces
    fun produceLogger(injectionPoint: InjectionPoint): Logger {
        return LoggerFactory.getLogger(injectionPoint.member.declaringClass)
    }
}