package com.example.tiles;

import junit.framework.TestCase;

public class ItemTileTest extends TestCase {
    public void testItemTileCharacterMatchesType() {
        ItemTile goldTile = new ItemTile(ItemType.GOLD);
        ItemTile potionTile = new ItemTile(ItemType.POTION);

        assertEquals('G', goldTile.getType());
        assertEquals('P', potionTile.getType());
    }
}