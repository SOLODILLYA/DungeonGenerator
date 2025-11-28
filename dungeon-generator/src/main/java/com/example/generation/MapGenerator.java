package com.example.generation;

import java.util.ArrayList;

import com.example.core.DungeonMap;
import com.example.core.Move;
import com.example.core.Position;
import com.example.core.Tile;
import com.example.core.TileType;
import com.example.tiles.ItemTile;
import com.example.tiles.ItemType;

public class MapGenerator {
    Position startPosition = new Position(0, 0);
    Position exitPosition = new Position(0, 0);

    public void generateMap(DungeonMap dungeonMap) {
        generateStart(dungeonMap);
        generateExit(dungeonMap);
        generatePath(dungeonMap);
        generateRooms(dungeonMap);
    }

    private void generateStart(DungeonMap dungeonMap) {
        startPosition = new Position((int) (Math.random() * dungeonMap.width), 0);
        dungeonMap.setTile(startPosition, new Tile(TileType.START));
    }

    private void generateExit(DungeonMap dungeonMap) {
        exitPosition = new Position((int) (Math.random() * dungeonMap.width), dungeonMap.height - 1);
        dungeonMap.setTile(exitPosition, new Tile(TileType.EXIT));
    }

    private void generatePath(DungeonMap dungeonMap) {
        Position currentPos = startPosition;
        while (!currentPos.equals(exitPosition)) {
            Move[][] moves = getPossibleMoves(dungeonMap, currentPos);
            if (Math.random() < 0.6 && moves[0].length > 0) {
                Move move = moves[0][(int) (Math.random() * moves[0].length)];
                currentPos = movePosition(currentPos, move);
                if (!currentPos.equals(exitPosition) && !currentPos.equals(startPosition)) {
                    dungeonMap.setTile(currentPos, new Tile(TileType.FLOOR));
                }
            } else if (moves[1].length > 0) {
                Move move = moves[1][(int) (Math.random() * moves[1].length)];
                currentPos = movePosition(currentPos, move);
                if (!currentPos.equals(exitPosition) && !currentPos.equals(startPosition)) {
                    dungeonMap.setTile(currentPos, new Tile(TileType.FLOOR));
                }
            }

        }
    }

    private Move[][] getPossibleMoves(DungeonMap dungeonMap, Position pos) {
        ArrayList<Move> goodMoves = new ArrayList<Move>();
        ArrayList<Move> badMoves = new ArrayList<Move>();
        if (pos.x > 0) {
            if (pos.x > exitPosition.x) {
                goodMoves.add(Move.LEFT);
            } else {
                badMoves.add(Move.LEFT);
            }
        }
        if (pos.x < dungeonMap.width - 1) {
            if (pos.x < exitPosition.x) {
                goodMoves.add(Move.RIGHT);
            } else {
                badMoves.add(Move.RIGHT);
            }
        }
        if (pos.y > 0) {
            if (pos.y > exitPosition.y) {
                goodMoves.add(Move.UP);
            } else {
                badMoves.add(Move.UP);
            }
        }
        if (pos.y < dungeonMap.height - 1) {
            if (pos.y < exitPosition.y) {
                goodMoves.add(Move.DOWN);
            } else {
                badMoves.add(Move.DOWN);
            }
        }
        return new Move[][] { goodMoves.toArray(new Move[0]), badMoves.toArray(new Move[0]) };
    }

    private Position movePosition(Position pos, Move move) {
        switch (move) {
            case UP:
                return new Position(pos.x, pos.y - 1);
            case DOWN:
                return new Position(pos.x, pos.y + 1);
            case LEFT:
                return new Position(pos.x - 1, pos.y);
            case RIGHT:
                return new Position(pos.x + 1, pos.y);
            default:
                return pos;
        }
    }

    public ArrayList<Position> getPossiblePositions(DungeonMap dungeonMap) {
        ArrayList<Position> possiblePositions = new ArrayList<Position>();
        for (int i = 1; i < dungeonMap.width - 1; i++) {
            for (int j = 0; j < dungeonMap.height - 2; j++) {
                if (dungeonMap.tiles[i][j].type == TileType.FLOOR &&
                        ((i == 0) || (dungeonMap.tiles[i - 1][j].type == TileType.FLOOR
                                || dungeonMap.tiles[i - 1][j].type == TileType.WALL))
                        &&
                        ((i == dungeonMap.width - 1) || (dungeonMap.tiles[i + 1][j].type == TileType.FLOOR
                                || dungeonMap.tiles[i + 1][j].type == TileType.WALL))
                        &&
                        ((j == 0) || (dungeonMap.tiles[i][j - 1].type == TileType.FLOOR
                                || dungeonMap.tiles[i][j - 1].type == TileType.WALL))
                        &&
                        ((j == dungeonMap.height - 1) || (dungeonMap.tiles[i][j + 1].type == TileType.FLOOR
                                || dungeonMap.tiles[i][j + 1].type == TileType.WALL))) {
                    possiblePositions.add(new Position(i, j));
                }
            }
        }
        return possiblePositions;
    }

    public DungeonMap spawnMonsters(DungeonMap dungeonMap) {
        ArrayList<Position> monsterPositions = getPossiblePositions(dungeonMap);
        int monstersToSpawn = monsterPositions.size() / 20;
        while (monstersToSpawn > 0 && monsterPositions.size() > 0) {
            int index = (int) (Math.random() * monsterPositions.size());
            Position pos = monsterPositions.get(index);
            dungeonMap.setTile(pos, new Tile(TileType.MONSTER));
            monsterPositions.remove(index);
            monstersToSpawn--;
        }
        return dungeonMap;
    }

    public DungeonMap spawnItems(DungeonMap dungeonMap) {
        ArrayList<Position> itemPositions = getPossiblePositions(dungeonMap);
        int itemsToSpawn = itemPositions.size() / 10;
        while (itemsToSpawn > 0 && itemPositions.size() > 0) {
            int index = (int) (Math.random() * itemPositions.size());
            Position pos = itemPositions.get(index);
            int type = (int) (Math.random() * 4);
            ItemType itemType;
            switch (type) {
                case 0:
                    itemType = ItemType.GOLD; // GOLD
                    break;
                case 1:
                    itemType = ItemType.POTION; // POTION
                    break;
                case 2:
                    itemType = ItemType.KEY; // KEY
                    break;
                case 3:
                    itemType = ItemType.WEAPON; // WEAPON
                    break;
                default:
                    itemType = ItemType.GOLD;
                    break;
            }
            dungeonMap.setTile(pos, new ItemTile(itemType));
            itemPositions.remove(index);
            itemsToSpawn--;
        }
        return dungeonMap;
    }

    public DungeonMap generateRooms(DungeonMap dungeonMap) {
        ArrayList<Position> roomPositions = getPossiblePositions(dungeonMap);
        int roomsToSpawn = roomPositions.size() / 7;
        while (roomsToSpawn > 0 && roomPositions.size() > 0) {
            int index = (int) (Math.random() * roomPositions.size());
            Position pos = roomPositions.get(index);
            int roomWidth = 3 + (int) (Math.random() * 5);
            int roomHeight = 3 + (int) (Math.random() * 5);
            for (int x = pos.x; x < pos.x + roomWidth && x < dungeonMap.width - 1; x++) {
                for (int y = pos.y; y < pos.y + roomHeight && y < dungeonMap.height - 1; y++) {
                    dungeonMap.setTile(new Position(x, y), new Tile(TileType.FLOOR));
                }
            }
            roomPositions.remove(index);
            roomsToSpawn--;
        }

        return dungeonMap;
    }
}
