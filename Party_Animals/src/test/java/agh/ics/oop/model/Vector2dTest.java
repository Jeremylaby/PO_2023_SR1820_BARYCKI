package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.security.KeyStore;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void testToString() {
        Vector2d vector2d=new Vector2d(2,3);
        assertEquals("(2,3)",vector2d.toString());
    }

    @Test
    void precedes() {
        Vector2d vector2d1=new Vector2d(2,3);
        Vector2d vector2d2=new Vector2d(3,3);
        Vector2d vector2d3=new Vector2d(3,4);
        assertEquals(true,vector2d1.precedes(vector2d2));
        assertEquals(true,vector2d1.precedes(vector2d3));
        assertEquals(false,vector2d3.precedes(vector2d2));

    }

    @Test
    void follows() {
        Vector2d vector2d1=new Vector2d(2,3);
        Vector2d vector2d2=new Vector2d(3,3);
        Vector2d vector2d3=new Vector2d(3,4);
        assertEquals(false,vector2d1.follows(vector2d2));
        assertEquals(false,vector2d1.follows(vector2d3));
        assertEquals(true,vector2d3.follows(vector2d2));
    }

    @Test
    void add() {
        Vector2d vector2d1=new Vector2d(1,1);
        Vector2d vector2d2=new Vector2d(2,2);
        assertEquals(vector2d2,vector2d1.add(vector2d1));
    }

    @Test
    void subtract() {
        Vector2d vector2d1=new Vector2d(1,1);
        Vector2d vector2d2=new Vector2d(0,0);
        assertEquals(vector2d2,vector2d1.subtract(vector2d1));
    }

    @Test
    void upperRight() {
        Vector2d vector2d1=new Vector2d(-1,1);
        Vector2d vector2d2=new Vector2d(1,-1);
        Vector2d vector2d3=new Vector2d(1,1);
        assertEquals(vector2d3,vector2d1.upperRight(vector2d2));
    }

    @Test
    void lowerLeft() {
        Vector2d vector2d1=new Vector2d(-1,1);
        Vector2d vector2d2=new Vector2d(1,-1);
        Vector2d vector2d3=new Vector2d(-1,-1);
        assertEquals(vector2d3,vector2d1.lowerLeft(vector2d2));

    }

    @Test
    void opposite() {
        Vector2d vector2d1=new Vector2d(1,1);
        Vector2d vector2d2=new Vector2d(-1,-1);
        assertEquals(vector2d2,vector2d1.opposite());
    }

    @Test
    void testEquals() {
        Vector2d vector2d1=new Vector2d(1,1);
        Vector2d vector2d2=new Vector2d(-1,-1);
        Vector2d vector2d3=new Vector2d(1,1);
        assertEquals(false,vector2d1.equals(vector2d2));
        assertEquals(true,vector2d1.equals(vector2d1));
        assertEquals(true,vector2d1.equals(vector2d3));

    }
}