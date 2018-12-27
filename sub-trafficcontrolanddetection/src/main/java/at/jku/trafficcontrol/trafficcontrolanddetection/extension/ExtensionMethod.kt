package at.jku.trafficcontrol.trafficcontrolanddetection.extension

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Synchronizes the current context with the task.
 */
fun Any.sync(f: suspend () -> Unit) {
    GlobalScope.launch(Dispatchers.Default) {
        f.invoke()
    }
}