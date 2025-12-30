# Project Overview

This project is a simple command-line based text adventure game written in Java. The user navigates through a procedurally generated dungeon by entering directions (north, south, east, west).

## Building and Running

### Prerequisites

*   Java 11+
*   Maven

### Building the Project

To build the project and create a runnable JAR file, run the following command in the `java-cli` directory:

```bash
mvn package
```

### Running the Game

Once the project is built, you can run the game using the following command in the `java-cli` directory:

```bash
java -jar target/java-cli-0.1.0.jar
```

Alternatively, you can run the game using the Maven exec plugin:

```bash
mvn exec:java
```

## Development Conventions

The project follows standard Java conventions. The code is organized into a single package `com.example.cli`.

*   `Main.java`: The entry point of the application. It creates a `Loop` object and starts the game.
*   `Loop.java`: Contains the main game loop, user input handling, and procedural generation of new rooms.
*   `Node.java`: Represents a room in the dungeon. It stores the room's description and the connections to other rooms.
