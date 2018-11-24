package at.jku.controlsystem;

import at.jku.controlsystem.demo.Calculator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/controlsystem")
@ApplicationScoped
public class ControlSystemApplication extends Application {
    private final Calculator calculator = new Calculator();

    /**
     * This is the startup method of this subsystem. It acts basically the same as
     * static.void.main(String[] args). A basic sample to code business logic is provided below.
     */
    @PostConstruct
    public void onStartup() {
        // Content can be replaced by your implementation.

        System.out.println("Hello World!");

        int result = this.calculator.additition(5, 2);
        System.out.println("5 + 2 = " + result);

        // The server does not stop after this function has finished.
    }
}
