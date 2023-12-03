package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {

    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    private List<MapChangeListener> observers= new ArrayList<>();
    public Map<Vector2d, Animal> getAnimals() {
        return Map.copyOf(animals);
    }
    public void addObserver(MapChangeListener observer){
        observers.add(observer);
    }
    public void removeObserver(MapChangeListener observer){
        observers.remove(observer);
    }
    protected void mapChanged(String message){
        for(MapChangeListener observer:observers){
            observer.mapChanged(this,message);
        }
    }
    public boolean place(Animal animal) throws PositionAlreadyOccupiedException {
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            mapChanged("Animal was placed on: " + animal.getPosition().toString());
            return true;
        }
        throw new PositionAlreadyOccupiedException(animal.getPosition());
    }

    public void move(Animal animal, MoveDirection direction) {
        if (this.isOccupied(animal.getPosition()) && this.objectAt(animal.getPosition()).equals(animal)) {
            Vector2d oldPosition = animal.getPosition();
            animals.remove(animal.getPosition());
            animal.move(direction, this);
            animals.put(animal.getPosition(), animal);
            if(!oldPosition.equals(animal.getPosition())) {//Nie wiem czy mam sygnalizować każdą próbę ruchu czy tyl;ko faktyczną zmianę więc zostawiam to tak
                mapChanged("Animal was moved from " + oldPosition.toString() + " to " + animal.getPosition().toString());
            }
        }
    }
    public boolean canMoveTo(Vector2d position) {

        return !animals.containsKey(position);
    }
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }



    @Override
    public Map<Vector2d, WorldElement> getElements() {
        Map<Vector2d,WorldElement> worldElementMap= new HashMap<>();
        for (Animal animal: animals.values()){
            worldElementMap.put(animal.getPosition(),animal);
        }
        return worldElementMap;
    }

    @Override
    public abstract Boundary getCurrentBounds();
    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        Boundary boundary= getCurrentBounds();
        return visualizer.draw(boundary.leftLower(),boundary.rightUpper());
    }
}
