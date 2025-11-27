package com.example;

public class App {
    public static void main(String[] args) {
        System.out.println("Dungeon Generator");
        DungeonMapController controller = new DungeonMapController();
        controller.initializeDungeonMap(10, 10);
        controller.spawnMonsters();
        DungeonMap dungeonMap = controller.getDungeonMap();
        System.out.println(dungeonMap.toString());
    }
}
