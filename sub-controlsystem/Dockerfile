FROM trafficcontrol

EXPOSE 8084
COPY target/sub-controlsystem-1.0.0-SNAPSHOT-thorntail.jar /thorntail/controlsystem.jar

ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "/thorntail/controlsystem.jar"]

