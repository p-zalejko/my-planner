#!/bin/bash

mvn package
podman build -f src/main/docker/Dockerfile.jvm -t pzalejko/my-planner .
podman-compose -f src/main/docker/docker-compose.yml up