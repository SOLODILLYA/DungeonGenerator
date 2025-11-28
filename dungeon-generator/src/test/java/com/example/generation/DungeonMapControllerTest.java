package com.example.generation;

import com.example.core.DungeonMap;
import com.example.core.TileType;

import junit.framework.TestCase;

public class DungeonMapControllerTest extends TestCase {
    public void testInitializeDungeonMapCreatesStartAndExit() {
        DungeonMapController controller = new DungeonMapController();
        controller.initializeDungeonMap(8, 8);
        DungeonMap map = controller.getDungeonMap();

        assertNotNull(map);
        assertEquals(8, map.width);
        assertEquals(8, map.height);

        int startCount = 0;
        int exitCount = 0;
        for (int x = 0; x < map.width; x++) {
            if (map.tiles[x][0].type == TileType.START) {
                startCount++;
            }
            if (map.tiles[x][map.height - 1].type == TileType.EXIT) {
                exitCount++;
            }
        }

        assertEquals(1, startCount);
        assertEquals(1, exitCount);
    }
}