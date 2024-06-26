package agh.ics.oop.model;

import java.util.Objects;

public class Vector2d {
    private final int x;
    private final int y;
    public Vector2d(int x,int y){
        this.x=x;
        this.y=y;
    }
    public int getY(){return y;}
    public int getX(){return x;}

    public String toString() {
        return String.format("(%d,%d)",x,y);
    }
    public boolean precedes(Vector2d other){
        return (this.x<=other.x && this.y<=other.y);
    }
    public boolean follows(Vector2d other){
        return (this.x>= other.x && this.y>=other.y);
    }
    public Vector2d add(Vector2d other){
        return new Vector2d(this.x+other.x,this.y+other.y);
    }
    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x-other.x,this.y-other.y);
    }
    public Vector2d upperRight(Vector2d other){
        return new Vector2d(Math.max(x,other.x),Math.max(y,other.y)) ;
    }
    public Vector2d lowerLeft(Vector2d other){
        return new Vector2d(Math.min(x,other.x),Math.min(y,other.y));
    }
    public Vector2d opposite(){return new Vector2d(-this.x,-this.y);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x && y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
