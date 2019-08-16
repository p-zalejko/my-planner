# Notes
Notes taken while working on this project.


# GraalVM installation

Use <a href="https://sdkman.io/">SDKMAN</a> for managing all Java version on your PC:
- ``sdk list java`` gives you a list of avaialable Java versions
- ``sdk install java <version`` installs a selected Java. For example: ``sdk install java 19.1.1-grl``

Ones the GraalVM is installed  you might need <a href="https://www.graalvm.org/docs/reference-manual/aot-compilation/">native image</a>:
- go to the ``~/.sdkman/candidates/java/<graalvm-version>/bin``
- execute: ``./gu install native-image``

# Quarkus and Kotlin - hello world
https://github.com/p-zalejko/my-planner/tree/v0.0.2
