package com.example.core;

import junit.framework.TestCase;

public class TileTest extends TestCase {
    public void testWallCharacter() {
        Tile wall = new Tile(TileType.WALL);
        assertEquals('#', wall.getType());
    }

    public void testFloorCharacter() {
        Tile floor = new Tile(TileType.FLOOR);
        assertEquals('.', floor.getType());
    }

    public void testSpecialCharacters() {
        Tile start = new Tile(TileType.START);
        Tile exit = new Tile(TileType.EXIT);
        Tile monster = new Tile(TileType.MONSTER);

        assertEquals('S', start.getType());
        assertEquals('E', exit.getType());
        assertEquals('M', monster.getType());
    }
}