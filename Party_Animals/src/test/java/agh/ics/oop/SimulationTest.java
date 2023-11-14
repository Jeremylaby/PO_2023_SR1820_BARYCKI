package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimulationTest {

    @Test
    void run() {
        String[] args ={"f","x","r","f","l","f","l","b"};
        List<MoveDirection> moves = List.of(MoveDirection.FORWARD,MoveDirection.RIGHT
                ,MoveDirection.FORWARD,MoveDirection.LEFT,MoveDirection.FORWARD,
                MoveDirection.LEFT,MoveDirection.BACKWARD);
        List<Vector2d> vector2ds=List.of(new Vector2d(0,0));
        Simulation simulation=new Simulation(vector2ds,OptionsParser.parse(args));
        assertIterableEquals(simulation.getMoves(),moves);
        simulation.run();
        assertTrue(simulation.getAnimals().get(0).getPosition().equals(new Vector2d(2,2)));
        assertTrue(simulation.getAnimals().get(0).getOrientation().equals(MapDirection.WEST));
        List<Vector2d> vector2ds2=List.of(new Vector2d(0,0),new Vector2d(4,4));
        String[] args2 ={"b","f","r","r","b","f","r","r","f","b","r","r","f","b"};
        Simulation simulation2=new Simulation(vector2ds2,OptionsParser.parse(args2));
        simulation2.run();
        assertTrue(simulation2.getAnimals().get(0).getPosition().equals(new Vector2d(0,0)));
        assertTrue(simulation2.getAnimals().get(1).getPosition().equals(new Vector2d(4,4)));

    }
}