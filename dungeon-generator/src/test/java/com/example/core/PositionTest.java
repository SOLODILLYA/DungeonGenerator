package com.example.core;

import junit.framework.TestCase;

public class PositionTest extends TestCase {
    public void testEqualsWithSameCoordinates() {
        Position first = new Position(2, 3);
        Position second = new Position(2, 3);

        assertEquals(first, second);
    }

    public void testEqualsWithDifferentCoordinates() {
        Position first = new Position(2, 3);
        Position second = new Position(4, 3);

        assertFalse(first.equals(second));
    }

    public void testEqualsWithDifferentObjectType() {
        Position position = new Position(1, 1);

        assertFalse(position.equals("not a position"));
    }

    public void testEqualsWithNull() {
        Position position = new Position(1, 1);

        assertFalse(position.equals(null));
    }
}