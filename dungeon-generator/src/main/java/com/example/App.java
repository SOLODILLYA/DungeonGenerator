package com.example;

public class App {
    public static void main(String[] args) {
        System.out.println("Dungeon Generator");
        DungeonMapController controller = new DungeonMapController();
        controller.initializeDungeonMap(30, 30);
        controller.spawnMonsters();
        controller.spawnItems();
        DungeonMap dungeonMap = controller.getDungeonMap();
        System.out.println(dungeonMap.toString());
    }
}
