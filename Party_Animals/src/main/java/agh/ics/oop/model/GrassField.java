package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class GrassField implements WorldMap {
    private final Map<Vector2d,Grass> grasses=new HashMap<>();
    private final Map<Vector2d,Animal> animals=new HashMap<>();

    public GrassField( int n) {
        //TODO losowanie bez powtórzeń miejsc na których ma być trawa
        // pozycje (0, 0) - (sqrt(n*10), sqrt(n*10))
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
    return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {


    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return null;
    }
}
