# Sample Quarkus and Kotlin project
- http://quarkus.io
- https://kotlinlang.org/

# How to build

- Compile the project: 

``mvn package -Pnative -Dnative-image.docker-build=true``

- Build a docker image:

``docker build -f src/main/docker/Dockerfile.native -t pzalejko/my-planner .``

- Launch:

``docker run -i --rm -p 8080:8080 pzalejko/my-planner``

