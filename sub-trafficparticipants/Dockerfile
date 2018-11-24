FROM trafficcontrol

EXPOSE 8081
COPY target/sub-trafficparticipants-1.0.0-SNAPSHOT-thorntail.jar /thorntail/trafficparticipants.jar

ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "/thorntail/trafficparticipants.jar"]

