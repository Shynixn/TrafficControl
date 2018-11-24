#!/bin/bash

if [[ $EUID -ne 0 ]]; then
    echo "Please run as root"
    exit 1
fi

docker build . -t trafficcontrol &&
docker build ./sub-controlsystem/ -t controlsystem &&
docker build ./sub-roadmaintenance/ -t roadmaintenance &&
docker build ./sub-trafficcontrolanddetection/ -t trafficcontrolanddetection &&
docker build ./sub-trafficparticipants/ -t trafficparticipants
