package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimulationTest {

    @Test//Stare testy ale poprawione
    void run1() {
        RectangularMap rectangularMap1=new RectangularMap(5,5);
        String[] args ={"f","x","r","f","l","f","l","b"};
        List<MoveDirection> moves = List.of(MoveDirection.FORWARD,MoveDirection.RIGHT
                ,MoveDirection.FORWARD,MoveDirection.LEFT,MoveDirection.FORWARD,
                MoveDirection.LEFT,MoveDirection.BACKWARD);
        List<Vector2d> vector2ds=List.of(new Vector2d(0,0));
        Simulation simulation=new Simulation(vector2ds,OptionsParser.parse(args),rectangularMap1);
        assertIterableEquals(simulation.getMoves(),moves);
        simulation.run();
        assertTrue(simulation.getAnimals().get(0).getPosition().equals(new Vector2d(2,2)));
        assertTrue(simulation.getAnimals().get(0).getOrientation().equals(MapDirection.WEST));

        RectangularMap rectangularMap2=new RectangularMap(5,5);
        List<Vector2d> vector2ds2=List.of(new Vector2d(0,0),new Vector2d(4,4));
        String[] args2 ={"b","f","r","r","b","f","r","r","f","b","r","r","f","b"};
        Simulation simulation2=new Simulation(vector2ds2,OptionsParser.parse(args2),rectangularMap2);
        simulation2.run();
        assertTrue(simulation2.getAnimals().get(0).getPosition().equals(new Vector2d(0,0)));
        assertTrue(simulation2.getAnimals().get(1).getPosition().equals(new Vector2d(4,4)));

    }
    @Test//Sprawdzamy czy możemy dodać więcej niż jedno zwierze na miejsce przed ipo symulacji
        // oraz czy przed i po symulacji odpowiednie zwierzęta są na tych samych miejscach
        // z perspektywy mapy i symulacji
    void run2(){
        List<Vector2d> postions=List.of(new Vector2d(0,0),new Vector2d(0,0)
                ,new Vector2d(0,0),new Vector2d(4,4),new Vector2d(4,9)
                ,new Vector2d(6,6),new Vector2d(6,6),new Vector2d(6,6)
                ,new Vector2d(6,6),new Vector2d(6,6),new Vector2d(6,6));
        String[] args1 ={"b","f","r","r","b","f","r","r","f","b","r","r","f","b"};
        RectangularMap rectangularMap=new RectangularMap(7,10);
        List<MoveDirection> moves = OptionsParser.parse(args1);
        Simulation simulation =new Simulation(postions,moves,rectangularMap);
        assertTrue(simulation.getAnimals().size()==4 && rectangularMap.getAnimals().size()==4);
        for(Animal animal:simulation.getAnimals()){
            assertTrue(animal.equals(rectangularMap.objectAt(animal.getPosition())));
        }
        assertTrue(simulation.getAnimals().get(0).getPosition().equals(new Vector2d(0,0)));
        assertTrue(simulation.getAnimals().get(1).getPosition().equals(new Vector2d(4,4)));
        assertTrue(simulation.getAnimals().get(2).getPosition().equals(new Vector2d(4,9)));
        assertTrue(simulation.getAnimals().get(3).getPosition().equals(new Vector2d(6,6)));
        simulation.run();
        assertTrue(simulation.getAnimals().size()==4 && rectangularMap.getAnimals().size()==4);
        for(Animal animal:simulation.getAnimals()){
            assertTrue(animal.equals(rectangularMap.objectAt(animal.getPosition())));
        }
    }

}