package at.jku.controlsystem.entity

class IncidentResponse{
    var type: String? = null
    var priority: String? = null
    var startDay: Int = 0
    var startMonth: Int = 0
    var startYear: Int = 0
    var endDay: Int = 0
    var endMonth: Int = 0
    var endYear: Int = 0
    var status: String? = null
    var assigned: String? = null
    var id: Int = 0

    constructor(type: String?, priority: String?, startDay: Int, startMonth: Int, startYear: Int, endDay: Int, endMonth: Int, endYear: Int, status: String?, assigned: String?) {
        this.type = type
        this.priority = priority
        this.startDay = startDay
        this.startMonth = startMonth
        this.startYear = startYear
        this.endDay = endDay
        this.endMonth = endMonth
        this.endYear = endYear
        this.status = status
        this.assigned = assigned
    }

    constructor()
}