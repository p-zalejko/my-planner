#!/bin/bash

mvn package -Pnative -Dnative-image.docker-build=true
podman build -f docker/Dockerfile.native -t pzalejko/my-planner .
