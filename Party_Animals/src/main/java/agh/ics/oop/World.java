package agh.ics.oop;

import agh.ics.oop.model.*;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.List;

public class World {
    private static void run(List<MoveDirection> args) {

        System.out.println("Start");
        for (MoveDirection arg : args) {
            String message = switch (arg) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case RIGHT -> "Zwierzak idzie w prawo";
                case LEFT -> "Zwierzak idzie w lewo";
            };
            System.out.println(message);

        }
        System.out.println("Stop");
    }

    public static void main(String[] args) {
        System.out.println("System wystartował");
        try {
            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2, 2));
            ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();

            List<Simulation> simulations = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                GrassField grassField = new GrassField(10);
                RectangularMap rectangularMap = new RectangularMap(5, 5);
                grassField.addObserver(consoleMapDisplay);

                rectangularMap.addObserver(consoleMapDisplay);
                WorldMap map = (i % 2 == 0) ? grassField : rectangularMap;
                simulations.add(new Simulation(positions, directions, map));
            }
            SimulationEngine engine = new SimulationEngine(simulations);
            engine.runAsyncInThreadPool();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("System zakończył działanie");

    }

}
