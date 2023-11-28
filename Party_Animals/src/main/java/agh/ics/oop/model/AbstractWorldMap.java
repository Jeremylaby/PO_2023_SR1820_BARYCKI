package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Grass> grasses = new HashMap<>();
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    public Map<Vector2d, Animal> getAnimals() {
        return Map.copyOf(animals);
    }
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position)!=null;
    }

    public void move(Animal animal, MoveDirection direction) {
        if (this.isOccupied(animal.getPosition()) && this.objectAt(animal.getPosition()).equals(animal)) {
            animals.remove(animal.getPosition());
            animal.move(direction, this);
            animals.put(animal.getPosition(), animal);
        }
    }
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    public String toString(Vector2d lowerLeft,Vector2d upperRight) {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft,upperRight);

    }

    @Override
    public Map<Vector2d, WorldElement> getElements() {
        Map<Vector2d,WorldElement> worldElementMap= new HashMap<>();
        for (Grass grass: grasses.values()){
            worldElementMap.put(grass.getPosition(),grass);
        }
        for (Animal animal: animals.values()){
            worldElementMap.put(animal.getPosition(),animal);
        }
        return worldElementMap;
    }
}
