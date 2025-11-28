package com.example.core;

import junit.framework.TestCase;

public class DungeonMapTest extends TestCase {
    public void testInitializesWithWalls() {
        DungeonMap map = new DungeonMap(4, 3);

        for (int x = 0; x < map.width; x++) {
            for (int y = 0; y < map.height; y++) {
                assertEquals(TileType.WALL, map.tiles[x][y].type);
            }
        }
    }

    public void testSetTileUpdatesGrid() {
        DungeonMap map = new DungeonMap(2, 2);
        Position position = new Position(1, 1);
        Tile floor = new Tile(TileType.FLOOR);

        map.setTile(position, floor);

        assertEquals(floor, map.tiles[position.x][position.y]);
    }

    public void testToStringFormat() {
        DungeonMap map = new DungeonMap(2, 2);
        map.setTile(new Position(0, 0), new Tile(TileType.FLOOR));
        map.setTile(new Position(1, 1), new Tile(TileType.START));

        String expected = "|.|#|\n" +
                "|#|S|\n";

        assertEquals(expected, map.toString());
    }
}