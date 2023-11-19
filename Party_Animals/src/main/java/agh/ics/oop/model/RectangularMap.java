package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private Map<Vector2d, Animal> animals = new HashMap<>();
    private final int width;
    private final int height;

    public RectangularMap(int width,int height) {
        this.width = width;
        this.height=height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Vector2d borderLeft=new Vector2d(0,0);
        Vector2d borderRight=new Vector2d(width-1,height-1);
        return (!isOccupied(position)&&position.follows(borderLeft)&& position.precedes(borderRight));
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        animal.move(direction);
    }

    @Override
    public boolean isOccupied(Vector2d position) {

    }

    @Override
    public Animal objectAt(Vector2d position) {
        return null;
    }
}
