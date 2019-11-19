#!/bin/bash

mvn clean package -Pnative -Dquarkus.native.container-runtime=podman
podman build -f docker/Dockerfile.native -t pzalejko/my-planner .
podman-compose -f docker/docker-compose.yml up