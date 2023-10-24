package agh.ics.oop;

import agh.ics.oop.model.MoveDirections;

import java.util.Arrays;

public class OptionsParser{
    public static MoveDirections[] convertToEnum(String[] args){
        int pos=0;
        MoveDirections[] directions= new MoveDirections[args.length];
        for(String arg:args){
            MoveDirections direction = switch (arg){
                case "f" -> MoveDirections.FORWARD;
                case "b" -> MoveDirections.BACKWARD;
                case "r" -> MoveDirections.RIGHT;
                case "l" -> MoveDirections.LEFT;
                default -> null;
            };
            if(direction!=null){
                directions[pos]=direction;
                pos+=1;
            }
        }
        directions= Arrays.copyOf(directions,pos);
       return directions;
    }

}
