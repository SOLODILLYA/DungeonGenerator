package com.example;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        
        Position pos = (Position) o;
        
        return Integer.compare(x, pos.x) == 0
                && Integer.compare(y, pos.y) == 0;
    }
}
