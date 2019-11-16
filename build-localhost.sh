#!/bin/bash

mvn package -Pnative -Dquarkus.native.container-runtime=podman
podman build -f src/main/docker/Dockerfile.native -t pzalejko/my-planner .
podman-compose -f src/main/docker/docker-compose.yml up