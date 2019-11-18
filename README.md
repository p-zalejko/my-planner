# Sample Quarkus and Kotlin project
- http://quarkus.io
- https://kotlinlang.org/

# How to build
In order to build and launch the application you need either [docker](https://www.docker.com/get-started) or [podman](https://podman.io/):

- Compile the project: 

``mvn package -Pnative -Dquarkus.native.container-runtime=podman``

- Build an image:

``podman build -f docker/Dockerfile.native -t pzalejko/my-planner .``

- Launch:

``podman-compose -f docker/docker-compose.yml up``

# More information
See https://p-zalejko.github.io/my-planner/
