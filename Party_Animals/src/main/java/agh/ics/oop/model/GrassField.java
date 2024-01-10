package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    public GrassField( int grassCount) {
        generateGrass(grassCount);
    }

    private void generateGrass(int grassCount) {
        int maxWidth= (int) Math.sqrt(grassCount *10);
        int maxHeight=maxWidth;
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxWidth, maxHeight, grassCount);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grasses.put(grassPosition, new Grass(grassPosition));
        }
    }


    public Map<Vector2d, Grass> getGrasses() {
        return Map.copyOf(grasses);
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position)||grasses.get(position)!=null;
    }



    @Override
    public Boundary getCurrentBounds() {
        Vector2d minVector=new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE);
        Vector2d maxVector=new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE);
        Map<Vector2d,WorldElement> worldElementMap=getElements();
        for(WorldElement worldElement:worldElementMap.values()){
            minVector=minVector.lowerLeft(worldElement.getPosition());
            maxVector=maxVector.upperRight(worldElement.getPosition());
        }
        return new Boundary(minVector,maxVector);
    }

    @Override
    public Map<Vector2d, WorldElement> getElements() {
        Stream<Grass> grassElements =grasses.values().stream().filter(grass -> !animals.containsKey(grass.getPosition()));
        return Stream.concat(grassElements,super.getElements().values().stream()).collect(Collectors.toMap(WorldElement::getPosition,worldElement -> worldElement));
    }

}
