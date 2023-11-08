package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void next() {
        MapDirection east = MapDirection.EAST;
        MapDirection west = MapDirection.WEST;
        MapDirection north = MapDirection.NORTH;
        MapDirection south = MapDirection.SOUTH;
        assertEquals(east,north.next());
        assertEquals(south,east.next());
        assertEquals(west,south.next());
        assertEquals(north,west.next());
    }

    @Test
    void previous() {
        MapDirection east = MapDirection.EAST;
        MapDirection west = MapDirection.WEST;
        MapDirection north = MapDirection.NORTH;
        MapDirection south = MapDirection.SOUTH;
        assertEquals(west,north.previous());
        assertEquals(south,west.previous());
        assertEquals(east,south.previous());
        assertEquals(north,east.previous());
    }
}