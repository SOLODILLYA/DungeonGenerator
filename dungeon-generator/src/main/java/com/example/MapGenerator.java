package com.example;

public class MapGenerator {
    public void generateMap(DungeonMap dungeonMap) {
        Position startPosition = new Position((int) (Math.random() * dungeonMap.width), 0);
        Position exitPosition = new Position((int) (Math.random() * dungeonMap.width), dungeonMap.height - 1);
        dungeonMap.setTile(startPosition, new Tile(TileType.START));
        dungeonMap.setTile(exitPosition, new Tile(TileType.EXIT));
    }
}
