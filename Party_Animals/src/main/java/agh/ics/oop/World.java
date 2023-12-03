package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    private static void run(List<MoveDirection> args){

        System.out.println("Start");
        for(MoveDirection arg:args){
            String message = switch (arg) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case RIGHT -> "Zwierzak idzie w prawo";
                case LEFT -> "Zwierzak idzie w lewo";
                default -> null; // w zasadzie nie potrzebne
            };
            if(message!=null){
                System.out.println(message);
            }
            }
        System.out.println("Stop");
    }
    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        GrassField grassField =new GrassField(10);
        RectangularMap rectangularMap = new RectangularMap(10,10);
        ConsoleMapDisplay consoleMapDisplay =new ConsoleMapDisplay();
        grassField.addObserver(consoleMapDisplay);
        rectangularMap.addObserver(consoleMapDisplay);
        Simulation simulation = new Simulation(positions, directions,grassField);
        simulation.run();
        Simulation simulation2 = new Simulation(positions, directions,rectangularMap);
        simulation2.run();
    }

}
