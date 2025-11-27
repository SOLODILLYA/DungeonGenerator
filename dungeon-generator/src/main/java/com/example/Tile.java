package com.example;

public class Tile {
    TileType type;

    public Tile(TileType type) {
        this.type = type;
    }

    public char getType() {
        return type.toString().charAt(0);
    }
}
