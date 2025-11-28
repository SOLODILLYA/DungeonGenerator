package com.example.generation;

import java.util.ArrayList;

import com.example.core.DungeonMap;
import com.example.core.Position;
import com.example.core.TileType;
import com.example.tiles.ItemTile;

import junit.framework.TestCase;

public class MapGeneratorTest extends TestCase {
    public void testGenerateMapCreatesBoundaries() {
        int width = 10;
        int height = 10;
        MapGenerator generator = new MapGenerator();
        DungeonMap map = new DungeonMap(width, height);

        generator.generateMap(map);

        int startCount = 0;
        int exitCount = 0;
        for (int x = 0; x < width; x++) {
            if (map.tiles[x][0].type == TileType.START) {
                startCount++;
            }
            if (map.tiles[x][height - 1].type == TileType.EXIT) {
                exitCount++;
            }
        }

        assertEquals(1, startCount);
        assertEquals(1, exitCount);
    }

    public void testGetPossiblePositionsReturnsAccessibleFloors() {
        DungeonMap map = new DungeonMap(5, 5);
        Position openFloor = new Position(1, 1);
        map.setTile(openFloor, new com.example.core.Tile(TileType.FLOOR));

        MapGenerator generator = new MapGenerator();
        ArrayList<Position> possiblePositions = generator.getPossiblePositions(map);

        assertEquals(1, possiblePositions.size());
        assertEquals(openFloor, possiblePositions.get(0));
    }

    public void testSpawnMonstersPlacesExpectedCount() {
        DungeonMap map = new DungeonMap(7, 7);
        MapGenerator generator = new MapGenerator();
        fillInteriorWithFloors(map);
        int possiblePositions = generator.getPossiblePositions(map).size();
        assertTrue(possiblePositions >= 20);

        generator.spawnMonsters(map);

        int monsters = countTilesOfType(map, TileType.MONSTER);
        assertEquals(possiblePositions / 20, monsters);
    }

    public void testSpawnItemsPlacesExpectedCount() {
        DungeonMap map = new DungeonMap(7, 7);
        MapGenerator generator = new MapGenerator();
        fillInteriorWithFloors(map);
        int possiblePositions = generator.getPossiblePositions(map).size();
        assertTrue(possiblePositions >= 20);

        generator.spawnItems(map);

        int items = countItemTiles(map);
        assertEquals(possiblePositions / 10, items);
    }

    public void testGenerateRoomsAddsAdditionalFloorSpace() {
        DungeonMap map = new DungeonMap(10, 10);
        MapGenerator generator = new MapGenerator();
        seedLinearPath(map);
        int originalFloors = countTilesOfType(map, TileType.FLOOR);

        generator.generateRooms(map);

        int updatedFloors = countTilesOfType(map, TileType.FLOOR);
        assertTrue(updatedFloors > originalFloors);
    }

    private void fillInteriorWithFloors(DungeonMap map) {
        for (int x = 1; x < map.width - 1; x++) {
            for (int y = 0; y < map.height - 2; y++) {
                map.setTile(new Position(x, y), new com.example.core.Tile(TileType.FLOOR));
            }
        }
    }

    private void seedLinearPath(DungeonMap map) {
        for (int y = 0; y < 7; y++) {
            map.setTile(new Position(1, y), new com.example.core.Tile(TileType.FLOOR));
        }
    }

    private int countTilesOfType(DungeonMap map, TileType type) {
        int count = 0;
        for (int x = 0; x < map.width; x++) {
            for (int y = 0; y < map.height; y++) {
                if (map.tiles[x][y].type == type) {
                    count++;
                }
            }
        }
        return count;
    }

    private int countItemTiles(DungeonMap map) {
        int count = 0;
        for (int x = 0; x < map.width; x++) {
            for (int y = 0; y < map.height; y++) {
                if (map.tiles[x][y] instanceof ItemTile) {
                    count++;
                }
            }
        }
        return count;
    }
}