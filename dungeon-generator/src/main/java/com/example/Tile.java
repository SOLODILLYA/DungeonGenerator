package com.example;

public class Tile {
    TileType type;

    public Tile(TileType type) {
        this.type = type;
    }

    public char getType() {
        if(type == TileType.WALL){
            return '#';
        }else if(type == TileType.FLOOR){
            return '.';
        }
        return type.toString().charAt(0);
    }
}
