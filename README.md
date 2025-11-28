# Dungeon Generator

A Java console application that creates a randomized dungeon layout. Players provide the desired width and height, and the generator produces a path from a random entrance to a random exit, sprinkles in rooms, monsters, and loot, and prints the resulting dungeon to the console.

## Features
- Command-line prompts for dungeon width and height (5–100 tiles).
- Randomized start and exit positions connected by a winding path.
- Room carving to expand open spaces off the main path.
- Monster and item placement with varied item types (gold, potions, keys, weapons).
- Manifest-configured JAR entry point (`com.example.App`) for easy execution.

## Requirements
- Java 17 or newer
- Maven 3.9 or newer

## Setup
1. Install the required Java and Maven versions.
2. From the repository root, build the project:
   ```bash
   mvn clean package
   ```
3. Run the console application using the generated JAR:
   ```bash
   java -jar target/dungeon-generator-1.0-SNAPSHOT.jar
   ```
4. When prompted, enter a dungeon width and height between 5 and 100. The application will print the generated map to the terminal.

## Testing
Run the unit tests with Maven:
```bash
mvn test
```
