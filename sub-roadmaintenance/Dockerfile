FROM trafficcontrol

EXPOSE 8083
COPY target/sub-roadmaintenance-1.0.0-SNAPSHOT-thorntail.jar /thorntail/roadmaintenance.jar

ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "/thorntail/roadmaintenance.jar"]

