package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest extends AbstractWorldMap {

    @Test//umieszczam wszystkie zwierzęta poza obszarem traw żeby sprawdzić
        // czy zwierze moze ruszyć się na pole z trawą i czy mapa jest w teori nieskończona
    void canMoveTo() {
        GrassField grassField = new GrassField(5);
        Vector2d vector2d1=new Vector2d(-1,-2);
        Vector2d vector2d2=new Vector2d(-4,-3);
        Vector2d vector2d3=new Vector2d(-1,0);
        Vector2d vector2d4=new Vector2d(-100,200);
        Vector2d vector2d5=new Vector2d(-400,300);
        Vector2d vector2d6=new Vector2d(-100,900);
        Vector2d vector2d7=new Vector2d(-10,9);
        List<Vector2d> vector2ds = List.of(vector2d2,vector2d3,vector2d4,vector2d5,vector2d6);
        Animal animal1=new Animal(vector2d1);
        Animal animal2=new Animal(vector2d7);
        grassField.place(animal1);
        grassField.place(animal2);
        for (Vector2d vector2d:vector2ds){
            assertTrue(grassField.canMoveTo(vector2d));
        }
        for(Grass grass:grassField.getGrasses().values()){
            assertTrue(grassField.canMoveTo(grass.getPosition()));
        }
        assertFalse(grassField.canMoveTo(vector2d1));
        assertFalse(grassField.canMoveTo(vector2d7));

    }

    @Test // sprawdzamy czy możemy umieścić zwierzę gdziekolwiek tylko nie jedno na drugim
    void place() {
        GrassField grassField = new GrassField(5);
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
        assertTrue(grassField.place(animal1));
        assertFalse(grassField.place(animal2));
        assertFalse(grassField.place(animal3));
        assertTrue(grassField.place(animal4));
        assertTrue(grassField.place(animal5));
        assertTrue(grassField.place(animal6));
    }
    @Test//czy możemy umieścić zwierzaki na trawie
    void place2(){
        GrassField grassField = new GrassField(5);
        List<Animal> animals=new ArrayList<>();
        for(Grass grass:grassField.getGrasses().values()){
            animals.add(new Animal(grass.getPosition()));
        }
        for(Animal animal:animals){
            assertTrue(grassField.place(animal));
        }
    }

    @Test//czy zwierze może wejść w zwierze
    void move() {
        GrassField grassfield =new GrassField(5);
        Vector2d vector2d1=new Vector2d(1,1);
        Vector2d vector2d2=new Vector2d(1,2);
        Vector2d vector2d3=new Vector2d(2,1);
        Animal animal1=new Animal(vector2d1);
        Animal animal2=new Animal(vector2d2);
        grassfield.place(animal1);
        grassfield.place(animal2);
        grassfield.move(animal1,MoveDirection.FORWARD);
        assertTrue(animal1.getPosition().equals(vector2d1));
        grassfield.move(animal2,MoveDirection.BACKWARD);
        assertTrue(animal2.getPosition().equals(vector2d2));
        grassfield.move(animal2,MoveDirection.RIGHT);
        grassfield.move(animal2,MoveDirection.FORWARD);
        grassfield.move(animal2,MoveDirection.RIGHT);
        grassfield.move(animal2,MoveDirection.FORWARD);
        grassfield.move(animal2,MoveDirection.RIGHT);
        grassfield.move(animal2,MoveDirection.FORWARD);
        assertTrue(animal2.getPosition().equals(vector2d3));
        grassfield.move(animal1,MoveDirection.RIGHT);
        grassfield.move(animal1,MoveDirection.FORWARD);
        assertTrue(animal1.getPosition().equals(vector2d1));
        assertTrue(grassfield.getAnimals().get(animal1.getPosition()).equals(animal1));
        assertTrue(grassfield.getAnimals().get(animal2.getPosition()).equals(animal2));
    }
    @Test//czy zwierze może sie ruszać teoretycznie gdziekolwiek w zasięgu int
    void move2() {
        GrassField grassfield =new GrassField(5);
        Vector2d vector2d1=new Vector2d(0,0);
        Vector2d vector2d2=new Vector2d(0,1000);
        Vector2d vector2d3=new Vector2d(1000,1000);
        Vector2d vector2d4=new Vector2d(1000,-1000);
        Vector2d vector2d5=new Vector2d(-1000,-1000);
        Animal animal1=new Animal(vector2d1);
        grassfield.place(animal1);
        for(int i = 0;i<1000;i++){
            grassfield.move(animal1,MoveDirection.FORWARD);
        }
        assertTrue(grassfield.getAnimals().get(vector2d2).equals(animal1));
        assertTrue(animal1.getPosition().equals(vector2d2));
        assertTrue(grassfield.getAnimals().get(animal1.getPosition()).equals(animal1));

        grassfield.move(animal1,MoveDirection.RIGHT);
        for(int i = 0;i<1000;i++){
            grassfield.move(animal1,MoveDirection.FORWARD);
        }
        assertTrue(grassfield.getAnimals().get(vector2d3).equals(animal1));
        assertTrue(animal1.getPosition().equals(vector2d3));
        assertTrue(grassfield.getAnimals().get(animal1.getPosition()).equals(animal1));

        grassfield.move(animal1,MoveDirection.RIGHT);
        for(int i = 0;i<2000;i++){
            grassfield.move(animal1,MoveDirection.FORWARD);
        }
        assertTrue(grassfield.getAnimals().get(vector2d4).equals(animal1));
        assertTrue(animal1.getPosition().equals(vector2d4));
        assertTrue(grassfield.getAnimals().get(animal1.getPosition()).equals(animal1));

        grassfield.move(animal1,MoveDirection.RIGHT);
        for(int i = 0;i<2000;i++){
            grassfield.move(animal1,MoveDirection.FORWARD);
        }
        assertTrue(grassfield.getAnimals().get(vector2d5).equals(animal1));
        assertTrue(animal1.getPosition().equals(vector2d5));
        assertTrue(grassfield.getAnimals().get(animal1.getPosition()).equals(animal1));
    }

    @Test//sprawdzamy czy occupied zwfraca true dla pól z trawami i pól ze zwierzakami
    void isOccupied() {
        GrassField grassfield =new GrassField(5);
        Vector2d vector2d1=new Vector2d(1,2);
        Vector2d vector2d2=new Vector2d(10,10);
        Vector2d vector2d3=new Vector2d(-1,0);
        Vector2d vector2d4=new Vector2d(0,-1);
        Vector2d vector2d5=new Vector2d(4,4);
        List<Vector2d> vector2ds=List.of(vector2d1,vector2d2,vector2d3,vector2d4,vector2d5);
        Animal animal1 = new Animal(vector2d1);
        Animal animal2 = new Animal(vector2d2);
        Animal animal3 = new Animal(vector2d3);
        Animal animal4 = new Animal(vector2d4);
        Animal animal5 = new Animal(vector2d5);
        List<Animal> animals = List.of(animal1,animal2,animal3,animal4,animal5);
        for (Animal animal:animals){
            grassfield.place(animal);
        }
        for(Vector2d vector2d:vector2ds){
            assertTrue(grassfield.isOccupied(vector2d));
        }
        for(Grass grass:grassfield.getGrasses().values()){
            assertTrue(grassfield.isOccupied(grass.getPosition()));
        }
    }

    @Test
    void objectAt() {
        GrassField grassField = new GrassField(5);
        Vector2d vector2d1=new Vector2d(-1,-2);
        Vector2d vector2d2=new Vector2d(-4,-3);
        Vector2d vector2d3=new Vector2d(-1,0);
        Vector2d vector2d4=new Vector2d(-100,200);
        Vector2d vector2d5=new Vector2d(-400,300);
        Vector2d vector2d6=new Vector2d(-100,900);
        Vector2d vector2d7=new Vector2d(-10,9);
        List<Vector2d> vector2ds = List.of(vector2d2,vector2d3,vector2d4,vector2d5,vector2d6);
        Animal animal1=new Animal(vector2d1);
        Animal animal2=new Animal(vector2d7);
        grassField.place(animal1);
        grassField.place(animal2);
        assertTrue(grassField.objectAt(vector2d1).equals(animal1));
        assertTrue(grassField.objectAt(vector2d7).equals(animal2));
        for (Vector2d vector2d:vector2ds){
            assertTrue(grassField.objectAt(vector2d)==null);
        }
        for (Grass grass:grassField.getGrasses().values()){
            assertTrue(grassField.objectAt(grass.getPosition()).equals(grass));
            assertTrue(grassField.objectAt(grass.getPosition()) instanceof Grass);
        }
    }
    @Test//stawiamy zwierzaki na trawie i sprawdzamy czy zwraca dobrze
    void objectAt2() {
        GrassField grassField = new GrassField(5);
        List<Animal> animals=new ArrayList<>();
        for(Grass grass:grassField.getGrasses().values()){
            animals.add(new Animal(grass.getPosition()));
        }
        for(Animal animal:animals){
            grassField.place(animal);
        }
        for(Animal animal:animals){
            assertTrue(grassField.objectAt(animal.getPosition()).equals(animal)&&grassField.objectAt(animal.getPosition()) instanceof Animal);
        }
    }
}