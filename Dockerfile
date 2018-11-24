#build as trafficcontrol

FROM openjdk:8-alpine

USER root

RUN mkdir /thorntail
WORKDIR /thorntail
