package agh.ics.opp;

public class World {
    private static void run(String[] args){
        int i =0;
        String tekst="";
        for(String arg:args){
            switch (arg){
                case "f" -> System.out.println("Zwierzak idzie do przodu");
                case "b" -> System.out.println("Zwierzak idzie do tyłu");
                case "r" -> System.out.println("Zwierzak idzie w prawo");
                case "l" -> System.out.println("Zwierzak idzie w lewo");
            }
            tekst+=arg;
            if (i< args.length-1){
                tekst+=",";
            }
            i++;
        }
        System.out.println(tekst);
        System.out.println("Zwierzak biegnie");
    }
    public static void main(String[] args) {
        System.out.println("System wystartowal");
        run(args);
        System.out.println("System zakończył działanie");
    }
}
