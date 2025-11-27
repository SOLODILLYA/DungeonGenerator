package com.example;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int width = 0;
        int height = 0;
        System.out.println("Dungeon Generator");
        System.out.println("-----------------");
        while (width < 5 || width > 100) {
            System.out.print("Enter dungeon width (5-100): ");
            if( scanner.hasNextInt()) {
                width = scanner.nextInt();
            }else{
                scanner.next();
            }
            
            if( width < 5 || width > 100){
                System.out.println("Invalid width. Please try again.");
            }
        }
        while (height < 5 || height > 100) {
            System.out.print("Enter dungeon height (5-100): ");
            if (scanner.hasNextInt()){
                height = scanner.nextInt();
            }else{
                scanner.next();
            }
            
            if (height < 5 || height > 100) {
                System.out.println("Invalid height. Please try again.");
            }
        }
        scanner.close();
        System.out.println("Entering Dungeon...");
        DungeonMapController controller = new DungeonMapController();
        controller.initializeDungeonMap(width, height);
        controller.spawnMonsters();
        controller.spawnItems();
        DungeonMap dungeonMap = controller.getDungeonMap();
        System.out.println(dungeonMap.toString());
    }
}
