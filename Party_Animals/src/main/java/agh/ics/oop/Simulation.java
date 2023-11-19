package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals;
    private List<MoveDirection> moves;
    private WorldMap worldMap;
    public List<Animal> getAnimals() {
        return animals;
    }

    public List<MoveDirection> getMoves() {
        return moves;
    }

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves,WorldMap worldMap) {
        this.worldMap=worldMap;
        this.animals=new ArrayList<>();
        for(Vector2d position:positions){
            Animal animal=new Animal(position);
            if(worldMap.place(animal)){
                animals.add(animal);
            }
        }
        this.moves=moves;
    }
    public void run(){
        int ind=0;
        for(MoveDirection move:moves){
            worldMap.move(animals.get(ind),move);
            System.out.println(worldMap.toString());
            ind+=1;
            ind%=animals.size();

        }
    }
}
