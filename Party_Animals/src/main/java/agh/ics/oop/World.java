package agh.ics.oop;

import agh.ics.oop.model.MoveDirections;

public class World {
    private static void run(MoveDirections[] args){
        /*int i =0;
        String tekst="";*/
        System.out.println("Start");
        for(MoveDirections arg:args){
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
            /*String message = switch (arg){
                case "f" -> "Zwierzak idzie do przodu";
                case "b" -> "Zwierzak idzie do tyłu";
                case "r" -> "Zwierzak idzie w prawo";
                case "l" -> "Zwierzak idzie w lewo";
                default -> null;
            };
            System.out.println(message);
            */
            /*tekst+=arg;
            if (i< args.length-1){
                tekst+=",";
            }*/
            }
            //i++;

        //System.out.println(tekst);
        System.out.println("Stop");
    }
    public static void main(String[] args) {
        System.out.println("System wystartowal");
        MoveDirections[] tab = OptionsParser.convertToEnum(args);
        run(tab);
        System.out.println("System zakończył działanie");
    }
}
