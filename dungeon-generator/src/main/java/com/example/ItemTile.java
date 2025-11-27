package com.example;

public class ItemTile extends Tile {
    private ItemType itemType;

    public ItemTile(ItemType itemType) {
        super(TileType.ITEM);
        this.itemType = itemType;
    }

    public char getType() {
        return itemType.toString().charAt(0);
    }   
}
