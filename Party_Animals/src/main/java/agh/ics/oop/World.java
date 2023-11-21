package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
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

    }
}
