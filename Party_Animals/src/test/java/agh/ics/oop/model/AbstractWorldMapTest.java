package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWorldMapTest {

    @Test
    void getOrderedAnimals() {
        Vector2d vector2d1 = new Vector2d(1,2);
        Vector2d vector2d2 = new Vector2d(1,3);
        Vector2d vector2d3 = new Vector2d(1,4);
        Vector2d vector2d4 = new Vector2d(2,1);
        Animal animal2= new Animal(vector2d1);
        Animal animal1= new Animal(vector2d2);
        Animal animal4= new Animal(vector2d3);
        Animal animal3= new Animal(vector2d4);
        List<Animal> animalsList=List.of(animal1,animal2,animal3,animal4);
        List<Animal> sortedanimals=List.of(animal2,animal1,animal4,animal3);

        RectangularMap rectangularMap =new RectangularMap(10,10);
        GrassField grassField =new GrassField(10);

        animalsList.forEach(animal -> {
            try {
                rectangularMap.place(animal);
                grassField.place(animal);
            } catch (PositionAlreadyOccupiedException e) {
                throw new RuntimeException(e);
            }
        });
        List<Animal> toTest=rectangularMap.getOrderedAnimals();
        for(int i = 0;i< toTest.size();i++){
            assertTrue(toTest.get(i).equals(sortedanimals.get(i)));
        }
        toTest=grassField.getOrderedAnimals();
        for(int i = 0;i< toTest.size();i++){
            assertTrue(toTest.get(i).equals(sortedanimals.get(i)));
        }
    }
    @Test
    void getOrderedAnimals2() {
        Vector2d vector2d1 = new Vector2d(-1,-2);
        Vector2d vector2d2 = new Vector2d(-1,-3);
        Vector2d vector2d3 = new Vector2d(-1,-4);
        Vector2d vector2d4 = new Vector2d(-2,-1);
        Animal animal2= new Animal(vector2d1);
        Animal animal1= new Animal(vector2d2);
        Animal animal4= new Animal(vector2d3);
        Animal animal3= new Animal(vector2d4);
        List<Animal> animalsList=List.of(animal1,animal2,animal3,animal4);
        List<Animal> sortedanimals=List.of(animal3,animal4,animal1,animal2);
        GrassField grassField =new GrassField(10);

        animalsList.forEach(animal -> {
            try {
                grassField.place(animal);
            } catch (PositionAlreadyOccupiedException e) {
                throw new RuntimeException(e);
            }
        });
        List<Animal> toTest=grassField.getOrderedAnimals();
        for(int i = 0;i< toTest.size();i++){
            assertTrue(toTest.get(i).equals(sortedanimals.get(i)));
        }
    }
    @Test
    void getOrderedAnimals3() {
        Vector2d vector2d1 = new Vector2d(1,1);
        Vector2d vector2d2 = new Vector2d(1,2);
        Vector2d vector2d3 = new Vector2d(1,3);
        Vector2d vector2d4 = new Vector2d(1,5);
        Vector2d vector2d5 = new Vector2d(1,6);
        Vector2d vector2d6 = new Vector2d(1,7);
        Vector2d vector2d7 = new Vector2d(1,8);
        Animal animal2= new Animal(vector2d1);
        Animal animal1= new Animal(vector2d2);
        Animal animal4= new Animal(vector2d3);
        Animal animal3= new Animal(vector2d4);
        Animal animal7= new Animal(vector2d5);
        Animal animal5= new Animal(vector2d6);
        Animal animal6= new Animal(vector2d7);
        List<Animal> animalsList=List.of(animal1,animal2,animal3,animal4,animal5,animal6,animal7);
        List<Animal> sortedanimals=List.of(animal2,animal1,animal4,animal3,animal7,animal5,animal6);

        RectangularMap rectangularMap =new RectangularMap(10,10);
        GrassField grassField =new GrassField(10);

        animalsList.forEach(animal -> {
            try {
                rectangularMap.place(animal);
                grassField.place(animal);
            } catch (PositionAlreadyOccupiedException e) {
                throw new RuntimeException(e);
            }
        });
        List<Animal> toTest=rectangularMap.getOrderedAnimals();
        for(int i = 0;i< toTest.size();i++){
            assertTrue(toTest.get(i).equals(sortedanimals.get(i)));
        }
        toTest=grassField.getOrderedAnimals();
        for(int i = 0;i< toTest.size();i++){
            assertTrue(toTest.get(i).equals(sortedanimals.get(i)));
        }
    }
}