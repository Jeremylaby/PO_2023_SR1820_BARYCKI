package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test//Testujemy czy zwierze może wyjść za mapę i czy może wejść w inne zwierze
    void canMoveTo() throws PositionAlreadyOccupiedException {
        RectangularMap rectangularMap=new RectangularMap(5,10);
        Vector2d vector2d1=new Vector2d(1,2);
        Vector2d vector2d2=new Vector2d(4,3);
        Vector2d vector2d3=new Vector2d(-1,0);
        Vector2d vector2d4=new Vector2d(0,-1);
        Vector2d vector2d5=new Vector2d(4,4);
        Vector2d vector2d6= new Vector2d(10,10);
        Vector2d vector2d7= new Vector2d(0,0);
        Vector2d vector2d8= new Vector2d(-1,-1);
        Animal animal1=new Animal(vector2d1);
        Animal animal2=new Animal(vector2d2);
        rectangularMap.place(animal1);
        rectangularMap.place((animal2));
        assertTrue(rectangularMap.canMoveTo(vector2d5));
        assertTrue(rectangularMap.canMoveTo(vector2d7));

        assertFalse(rectangularMap.canMoveTo(vector2d1));
        assertFalse(rectangularMap.canMoveTo(vector2d2));
        assertFalse(rectangularMap.canMoveTo(vector2d3));
        assertFalse(rectangularMap.canMoveTo(vector2d4));
        assertFalse(rectangularMap.canMoveTo(vector2d6));
        assertFalse(rectangularMap.canMoveTo(vector2d8));
    }

    @Test//testujemy możliwość umnieszczenia zwierzaka za mapą zwierzaka na zwierzaku
        // i czy zwierzak jest umieszony na dobrej popzycji

    void place() throws PositionAlreadyOccupiedException {
        RectangularMap rectangularMap=new RectangularMap(5,5);
        Vector2d vector2d1=new Vector2d(1,2);
        Vector2d vector2d2=new Vector2d(10,10);
        Vector2d vector2d3=new Vector2d(-1,0);
        Vector2d vector2d4=new Vector2d(0,-1);
        Vector2d vector2d5=new Vector2d(4,4);
        Animal animal1 = new Animal(vector2d1);
        Animal animal2 = new Animal(vector2d1);
        Animal animal3 = new Animal(vector2d1);
        Animal animal4 = new Animal(vector2d2);
        Animal animal5 = new Animal(vector2d3);
        Animal animal6= new Animal(vector2d4);
        Animal animal7 = new Animal(vector2d5);
        List<Animal> animals=List.of(animal1,animal2,animal3,animal4,animal5,animal6,animal7);
        for(Animal animal:animals){
            try{rectangularMap.place(animal);}
            catch (PositionAlreadyOccupiedException e){}
        }
        assertTrue(rectangularMap.getAnimals().size()==2);
        assertTrue(rectangularMap.getAnimals().get(vector2d1).equals(animal1));

        assertTrue(rectangularMap.getAnimals().get(vector2d5).equals(animal7));

        assertFalse(rectangularMap.getAnimals().get(vector2d1).equals(animal2));
        assertFalse(rectangularMap.getAnimals().get(vector2d1).equals(animal3));

        assertFalse(rectangularMap.getAnimals().containsKey(animal4.getPosition()));
        assertFalse(rectangularMap.getAnimals().containsKey(animal5.getPosition()));
        assertFalse(rectangularMap.getAnimals().containsKey(animal6.getPosition()));

    }

    @Test//zwierzaki próbują wyjść za mapę
    void move() throws PositionAlreadyOccupiedException {
        RectangularMap rectangularMap=new RectangularMap(5,10);
        Vector2d vector2d1=new Vector2d(0,9);
        Vector2d vector2d2=new Vector2d(4,0);
        Vector2d vector2d3=new Vector2d(0,0);
        Vector2d vector2d4=new Vector2d(4,9);
        Vector2d vector2d5=new Vector2d(4,0);
        Vector2d vector2d6=new Vector2d(0,9);
        Animal animal1=new Animal(vector2d1);
        Animal animal2=new Animal(vector2d2);
        rectangularMap.place(animal1);
        rectangularMap.place(animal2);
        for(int i =0;i<15;i++){
            rectangularMap.move(animal1,MoveDirection.BACKWARD);
            rectangularMap.move(animal2,MoveDirection.FORWARD);
        }
        assertTrue(rectangularMap.getAnimals().get(animal1.getPosition()).equals(animal1));
        assertTrue(rectangularMap.getAnimals().get(animal2.getPosition()).equals(animal2));
        assertTrue(animal1.getPosition().equals(vector2d3));
        assertTrue(animal2.getPosition().equals(vector2d4));
        rectangularMap.move(animal1,MoveDirection.RIGHT);
        rectangularMap.move(animal2,MoveDirection.LEFT);
        for(int i =0;i<15;i++){
            rectangularMap.move(animal1,MoveDirection.FORWARD);
            rectangularMap.move(animal2,MoveDirection.FORWARD);
        }
        assertTrue(rectangularMap.getAnimals().get(animal1.getPosition()).equals(animal1));
        assertTrue(rectangularMap.getAnimals().get(animal2.getPosition()).equals(animal2));
        assertTrue(animal1.getPosition().equals(vector2d5));
        assertTrue(animal2.getPosition().equals(vector2d6));
    }
    @Test//zwierzaki próbują wejść w siebie
    void move2() throws PositionAlreadyOccupiedException {
        RectangularMap rectangularMap=new RectangularMap(5,10);
        Vector2d vector2d1=new Vector2d(1,1);
        Vector2d vector2d2=new Vector2d(1,2);
        Vector2d vector2d3=new Vector2d(2,1);
        Animal animal1=new Animal(vector2d1);
        Animal animal2=new Animal(vector2d2);
        rectangularMap.place(animal1);
        rectangularMap.place(animal2);
        rectangularMap.move(animal1,MoveDirection.FORWARD);
        assertTrue(animal1.getPosition().equals(vector2d1));
        rectangularMap.move(animal2,MoveDirection.BACKWARD);
        assertTrue(animal2.getPosition().equals(vector2d2));
        rectangularMap.move(animal2,MoveDirection.RIGHT);
        rectangularMap.move(animal2,MoveDirection.FORWARD);
        rectangularMap.move(animal2,MoveDirection.RIGHT);
        rectangularMap.move(animal2,MoveDirection.FORWARD);
        rectangularMap.move(animal2,MoveDirection.RIGHT);
        rectangularMap.move(animal2,MoveDirection.FORWARD);
        assertTrue(animal2.getPosition().equals(vector2d3));
        rectangularMap.move(animal1,MoveDirection.RIGHT);
        rectangularMap.move(animal1,MoveDirection.FORWARD);
        assertTrue(animal1.getPosition().equals(vector2d1));
        assertTrue(rectangularMap.getAnimals().get(animal1.getPosition()).equals(animal1));
        assertTrue(rectangularMap.getAnimals().get(animal2.getPosition()).equals(animal2));


    }

    @Test
    void objectAt() throws PositionAlreadyOccupiedException {
        RectangularMap rectangularMap=new RectangularMap(5,10);
        Vector2d vector2d1=new Vector2d(0,9);
        Vector2d vector2d2=new Vector2d(4,0);
        Vector2d vector2d3=new Vector2d(0,0);
        Vector2d vector2d4=new Vector2d(4,9);
        Vector2d vector2d5=new Vector2d(4,5);
        Vector2d vector2d6=new Vector2d(1,9);
        List<Vector2d> vector2ds1=List.of(vector2d1,vector2d2,vector2d3,vector2d4,vector2d5,vector2d6);
        Vector2d vector2d7=new Vector2d(1,2);
        Vector2d vector2d8=new Vector2d(10,10);
        Vector2d vector2d9=new Vector2d(-1,0);
        Vector2d vector2d10=new Vector2d(0,-1);
        Vector2d vector2d11=new Vector2d(4,4);
        List<Vector2d> vector2ds2=List.of(vector2d7,vector2d8,vector2d9,vector2d10,vector2d11);
        Animal animal1=new Animal(vector2d1);
        Animal animal2=new Animal(vector2d2);
        Animal animal3=new Animal(vector2d3);
        Animal animal4=new Animal(vector2d4);
        Animal animal5=new Animal(vector2d5);
        Animal animal6=new Animal(vector2d6);
        List<Animal> animals=List.of(animal1,animal2,animal3,animal4,animal5,animal6);
        for(Animal animal:animals){
            rectangularMap.place(animal);
        }
        for(int i=0;i<animals.size();i++){
            assertTrue(rectangularMap.objectAt(vector2ds1.get(i)).equals(animals.get(i)));
        }
        for (Vector2d vector2d:vector2ds2){
            assertTrue(rectangularMap.objectAt(vector2d)==null);
        }


    }
}