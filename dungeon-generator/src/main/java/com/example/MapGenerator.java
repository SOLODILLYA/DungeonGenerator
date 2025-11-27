package com.example;

import java.util.ArrayList;

public class MapGenerator {
    Position startPosition = new Position(0, 0);
    Position exitPosition = new Position(0, 0);

    public void generateMap(DungeonMap dungeonMap) {
        generateStart(dungeonMap);
        generateExit(dungeonMap);
        generatePath(dungeonMap);
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
            Move[][] moves = possibleMoves(dungeonMap, currentPos);
            if (Math.random() < 0.7 && moves[0].length > 0) {
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

    private Move[][] possibleMoves(DungeonMap dungeonMap, Position pos) {
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
}
