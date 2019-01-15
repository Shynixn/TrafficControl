package at.jku.controlsystem.service

import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class RoadMaintenanceServiceTest{
    @Test
    fun `Instantiate Service`(){
        val roadMaintenanceService: RoadMaintenanceService = RoadMaintenanceServiceImpl()

        assertNotNull(roadMaintenanceService)
    }

    @Test
    fun `Request data from Road Maintenance`(){
        val roadMaintenanceService: RoadMaintenanceService = RoadMaintenanceServiceImpl()
        assertNotNull(roadMaintenanceService.getIncidents())
    }
}