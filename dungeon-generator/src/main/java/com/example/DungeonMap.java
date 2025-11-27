package com.example;

import java.util.ArrayList;

public class DungeonMap {
    public Tile[][] tiles;
    public int width;
    public int height;

    public DungeonMap(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(TileType.WALL);
            }
        }
    }

    public void setTile(Position pos, Tile tile) {
        tiles[pos.x][pos.y] = tile;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            sb.append('|');
            for (int x = 0; x < width; x++) {
                sb.append(tiles[x][y].getType());
                sb.append('|');
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    
}
