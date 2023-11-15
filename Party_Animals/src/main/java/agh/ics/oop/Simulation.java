package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals;
    private List<MoveDirection> moves;

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<MoveDirection> getMoves() {
        return moves;
    }

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves) {
        this.animals=new ArrayList<>();
        for(Vector2d position:positions){
            animals.add(new Animal(position));
        }
        this.moves=moves;
    }
    public void run(){
        int ind=0;
        for(MoveDirection move:moves){
            animals.get(ind).move(move);
            System.out.println("Zwierze " + (ind+1)+ ": "+animals.get(ind).getPosition().toString());
            ind+=1;
            ind%=animals.size();

        }
    }
}
