#!/bin/bash

if [[ $EUID -ne 0 ]]; then
    echo "Please run as root"
    exit 1
fi

docker kill $(docker ps -q)

./buildDocker.sh

docker run --network=host controlsystem &
docker run --network=host roadmaintenance &
docker run --network=host trafficcontrolanddetection &
docker run --network=host trafficparticipants &
