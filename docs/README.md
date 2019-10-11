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

[Quarkus and MongoDB - First Blood](https://github.com/p-zalejko/my-planner/commit/6b974978c214dadd089fdcb17cbe673bf84289db)

# CI/CD
## CircleCI
- [https://circleci.com/](https://circleci.com/)
- [https://circleci.com/integrations/github/](https://circleci.com/integrations/github/)
- [https://quarkus.io/blog/quarkus-app-circleci-build/](https://quarkus.io/blog/quarkus-app-circleci-build/)

## Azure Pipelines
- [https://github.com/marketplace/azure-pipelines](https://github.com/marketplace/azure-pipelines)
- [sample Java and Docker pipeline](https://github.com/p-zalejko/my-planner/blob/master/.azure/azure-pipeline.yml)

# Issue management
- [https://github.com/marketplace/gitkraken-glo-boards](https://github.com/marketplace/gitkraken-glo-boards)

# Other tools
- [https://github.com/JoeDog/siege](https://github.com/JoeDog/siege)
