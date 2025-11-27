package com.example;

public class DungeonMapController {
    DungeonMap dungeonMap;
    public void initializeDungeonMap(int width, int height) {
        dungeonMap = new DungeonMap(width, height);
        MapGenerator generator = new MapGenerator();
        generator.generateMap(dungeonMap);
    }

    public DungeonMap getDungeonMap() {
        return dungeonMap;
    }
}
