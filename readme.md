# Mowers simulation

Author: Tony Clonier

The main purpose of this application is to simulate the movement of mowers in a lawn.
Proposed solution to the subject located in the provided PDF file:
- Mowers can be identified by their position (cartesian coordinates) and orientation (NORTH, EAST, WEST, SOUTH).
- Mowers are independent (Mower class implements Runnable)
- Because they are independent, they need knowledge of the lawn they are in to check potential collisions with the other mowers.
- A FixedThreadPool is used in the simulation class to run the mowers.
- Because of the multi-threaded nature of the application, the output may be non determinist when mowers are susceptible to enter
in collision.

More information can be found in the generated Javadoc (see command below).

## Environnement
- Developped using Java openjdk 11.0.1
- Gradle 6.0 was used to generate this project structure: https://gradle.org/
- JUnit 5 library is used for unit tests: https://junit.org/junit5/

## Useful commands (UNIX)

- Run the application using provided gradlew script:
```
./gradlew run --args="src/main/resources/example.txt"
```
The argument must be the path to the file used to instantiate the simulation.

- Run the tests
```
./gradlew test
```

- Generate Javadoc
```
./gradlew javadoc
```
The generated Javadoc can be found in the "build/docs" directory.

## Simulation file Example

The file structure is described in the provided specifications.

The following "example.txt" file located in "src/main/resources/" directory

```
5 5
1 2 N
LFLFLFLFF
3 3 E
FFRFFRFRRF
```

produces the following output.
```
1 3 N
5 1 E
```
