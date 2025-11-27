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
        for(int x = 0; x < width; x++) {
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
            for (int x = 0; x < width; x++) {
                sb.append(tiles[x][y].getType());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public DungeonMap spawnMonsters(){
        ArrayList<Position> monsterPositions = new ArrayList<Position>();
        for(int i = 1; i < width - 1; i++){
            for(int j = 0; j < height - 2; j++){
                if(tiles[i][j].type == TileType.FLOOR){
                    monsterPositions.add(new Position(i, j));
                }
            }
        }
        int monstersToSpawn = monsterPositions.size() / 10;
        while (monstersToSpawn > 0 && monsterPositions.size() > 0){
            int index = (int)(Math.random() * monsterPositions.size());
            Position pos = monsterPositions.get(index);
            setTile(pos, new Tile(TileType.MONSTER));
            monsterPositions.remove(index);
            monstersToSpawn--;
        }
        return this;
    }
}
