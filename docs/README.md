# Notes
Notes taken while working on this project.


# GraalVM installation

Use [SDKMAN](https://sdkman.io/) for managing all Java version on your PC:
- ``sdk list java`` gives you a list of avaialable Java versions
- ``sdk install java <version`` installs a selected Java. For example: ``sdk install java 19.1.1-grl``

Ones the GraalVM is installed  you might need [native image](https://www.graalvm.org/docs/reference-manual/aot-compilation/):
- go to the ``~/.sdkman/candidates/java/<graalvm-version>/bin``
- execute: ``./gu install native-image``

# Quarkus and Kotlin - hello world
[sample](https://github.com/p-zalejko/my-planner/tree/v0.0.2)

[Quarkus and MongoDB - First Blood](https://github.com/p-zalejko/my-planner/tree/v0.0.3)

# Create RSA keys
If you need RSA keys then you can generate them in the following way:
- ```openssl genrsa -out private.pem 2048``` 
- ```openssl rsa -in private.pem -pubout -outform PEM -out publicKey.pem```
- ```openssl pkcs8 -topk8 -inform PEM -in private.pem -out privateKey.pem -nocrypt```

Use generated ```privateKey.pem``` and ```publicKey.pem```

# CI/CD
## CircleCI
- [https://circleci.com/](https://circleci.com/)
- [https://circleci.com/integrations/github/](https://circleci.com/integrations/github/)
- [https://quarkus.io/blog/quarkus-app-circleci-build/](https://quarkus.io/blog/quarkus-app-circleci-build/)

## Azure Pipelines
- [https://github.com/marketplace/azure-pipelines](https://github.com/marketplace/azure-pipelines)
- [sample Java and Docker pipeline](https://github.com/p-zalejko/my-planner/blob/master/.azure/azure-pipeline.yml)


# Development platforms
## Okteto 
You can create a small cluster at https://cloud.okteto.com. It allows for launching at testing applications directly in Kubernetes:irectly in Kubernetes 
- install CLI ```curl https://get.okteto.com -sSfL | sh```
- setup the project ```okteto init```
  - select the base image (in my case it's maven): ```okteto/maven:latest```
- launch ```oktekto up```
- launch the app in dev mode: ```mvn package quarkus:dev```
- open your app at Okteto: ```https://<APP>.cloud.okteto.net/```

## RedHat OpenShift
You can launch OpenShift 4.x on you laptop. Visit https://cloud.redhat.com/ and:
- download CodeReady Containers
- install by executing ```crc setup```
- launch a web console by executing ```crc console```

# Other tools
- [https://github.com/JoeDog/siege](https://github.com/JoeDog/siege)

# Issue management
- [https://github.com/marketplace/gitkraken-glo-boards](https://github.com/marketplace/gitkraken-glo-boards)

