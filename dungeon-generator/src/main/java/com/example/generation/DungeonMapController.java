package com.example.generation;

import com.example.core.DungeonMap;

public class DungeonMapController {
    DungeonMap dungeonMap;
    MapGenerator generator;
    public void initializeDungeonMap(int width, int height) {
        dungeonMap = new DungeonMap(width, height);
        generator = new MapGenerator();
        generator.generateMap(dungeonMap);
        spawnMonsters();
        spawnItems();
    }

    public DungeonMap getDungeonMap() {
        return dungeonMap;
    }

    public void spawnMonsters() {
        generator.spawnMonsters(dungeonMap);
    }

    public void spawnItems() {
        generator.spawnItems(dungeonMap);
    }
}
