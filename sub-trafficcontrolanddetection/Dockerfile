FROM trafficcontrol

EXPOSE 8082
COPY target/sub-trafficcontrolanddetection-1.0.0-SNAPSHOT-thorntail.jar /thorntail/trafficcontrolanddetection.jar

ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "/thorntail/trafficcontrolanddetection.jar"]

