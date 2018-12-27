package at.jku.trafficcontrol.trafficcontrolanddetection;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/trafficcontrolanddetection")
@ApplicationScoped
public class TrafficControlAndDetectionApplication extends Application {
    public static final String CONTROL_SYSTEM_URL = "http://localhost:8084/controlsystem";
    public static final String TRAFFIC_PARTICIPANTS_SYSTEM_URL = "http://localhost:8081/trafficparticiapnts";
    public static final String ROADMAINTENANCE_SYSTEM_URL = "http://localhost:8081/roadmaintenance";

    /**
     * This is the startup method of this subsystem. It acts basically the same as
     * static.void.main(String[] args). A basic sample to code business logic is provided below.
     */
    @PostConstruct
    public void onStartup() {
        System.out.println("Hello World!");
    }
}
