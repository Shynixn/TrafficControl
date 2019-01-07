package at.jku.trafficcontrol.trafficcontrolanddetection.entity

import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
open class Command {
    /**
     * Command Type.
     */
    var type : CommandType? = null
}