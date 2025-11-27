package com.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Dungeon Generator" );
        DungeonMap dungeonMap = new DungeonMap(10, 10);
        System.out.println(dungeonMap.toString());
    }
}
