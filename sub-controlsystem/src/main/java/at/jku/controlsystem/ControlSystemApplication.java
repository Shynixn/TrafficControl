package at.jku.controlsystem;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/controlsystem")
@ApplicationScoped
public class ControlSystemApplication extends Application {

    /**
     * This is the startup method of this subsystem. It acts basically the same as
     * static.void.main(String[] args). A basic sample to code business logic is provided below.
     */
    @PostConstruct
    public void onStartup() {

    }
}
