package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.Collections;
import java.util.HashMap;

import java.util.Map;

public class RectangularMap implements WorldMap {
    private static final Vector2d LEFT_LOWER_CORNER=new Vector2d(0,0);
    private final Vector2d rightuppercorner;
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final int width;
    private final int height;

    public RectangularMap(int width,int height) {
        rightuppercorner=new Vector2d(width-1,height-1);
        this.width = width;
        this.height=height;
    }

    public Map<Vector2d, Animal> getAnimals() {
        return Map.copyOf(animals);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (!isOccupied(position)&&position.follows(LEFT_LOWER_CORNER)&& position.precedes(rightuppercorner));
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (isOccupied(animal.getPosition()) && objectAt(animal.getPosition()).equals(animal)) {
            animals.remove(animal.getPosition());
            animal.move(direction, this);
            animals.put(animal.getPosition(), animal);
        }
    }


    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        Vector2d lowerLeft = new Vector2d(0, 0);
        Vector2d upperRight = new Vector2d(width - 1, height - 1);
        return visualizer.draw(lowerLeft, upperRight);
    }
    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position)!=null;
    }
}
