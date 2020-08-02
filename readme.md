# Mowers simulation

The main purpose of this application is to simulate the movement of mowers in a lawn.
Mowers can be identified by their position (cartesian coordinates) and orientation (NORTH, EAST, WEST, SOUTH).
A file is used to instantiate the simulation.

The following file:

```
5 5
1 2 N
LFLFLFLFF
3 3 E
FFRFFRFRRF
```

would produce the following output:
```
1 3 N
5 1 E
```

## Project structure and dependencies
- Gradle 6.0 was used to generate this project structure: https://gradle.org/
- JUnit 5 library is used for unit tests: https://junit.org/junit5/

## Run the application

- Unix: Run application using provided gradlew script (UNIX):
```
./gradlew run --args="src/main/resources/example.txt"
```

- Windows: Run application using provided gradlew script (UNIX):
```
gradlew.batch run --args="src/main/resources/example.txt"
```