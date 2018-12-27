package at.jku.trafficcontrol.trafficcontrolanddetection.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.xml.bind.annotation.XmlRootElement

/**
 * Command Entity.
 */
@XmlRootElement
open class Command {
    /**
     * Command Type.
     */
    lateinit var type: CommandType

    /**
     * Does this command have highest authority.
     */
    @JsonIgnore
    var highestAuthority: Boolean = false

    /**
     * Execution date.
     */
    @JsonIgnore
    var executionDate: Date = Date()
}