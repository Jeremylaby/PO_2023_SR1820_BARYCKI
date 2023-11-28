package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class GrassField extends AbstractWorldMap {

    public GrassField( int grassCount) {
        int maxWidth= (int) Math.sqrt(grassCount*10);
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
    public boolean canMoveTo(Vector2d position) {
        return animals.get(position)==null;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return canMoveTo(position)==true
                ? grasses.get(position)
                : animals.get(position);
    }
    @Override
    public String toString() {
        int maxX=0,minX=0,maxY=0,minY=0;
        for(Animal animal:animals.values()){
            maxX=Math.max(maxX,animal.getPosition().getX());
            maxY=Math.max(maxY,animal.getPosition().getY());
            minX=Math.min(minX,animal.getPosition().getX());
            minY=Math.min(minY,animal.getPosition().getY());
        }
        for(Grass grass:grasses.values()){
            if(canMoveTo(grass.getPosition())){
                maxX=Math.max(maxX,grass.getPosition().getX());
                maxY=Math.max(maxY,grass.getPosition().getY());
                minX=Math.min(minX,grass.getPosition().getX());
                minY=Math.min(minY,grass.getPosition().getY());
            }
        }
        Vector2d lowerLeft=new Vector2d(minX,minY);
        Vector2d upperRight=new Vector2d(maxX,maxY);
        return super.toString(lowerLeft,upperRight);
    }
}
