package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextMap implements WorldMap<String,Integer>{
    private Map<Integer, String> worldmap = new HashMap<>();
    private Map<String,MapDirection> orientations =new HashMap<>();
    @Override
    public boolean canMoveTo(Integer position) {
        return (position<worldmap.size() && position>=0);
    }

    @Override
    public boolean place(String object) {
        worldmap.put(worldmap.size(), object);
        orientations.put(object, MapDirection.NORTH);
        return true;
    }

    @Override
    public void move(String object, MoveDirection direction) {
        MapDirection orientation=orientations.get(object);
        orientation= switch (direction){
            case FORWARD,BACKWARD->orientation;
            case LEFT->orientation.previous();
            case RIGHT->orientation.next();
        };
        orientations.put(object,orientation);
        if(orientation == MapDirection.NORTH || orientation==MapDirection.SOUTH){
            return;
        }
        Integer move;
        move = switch (direction){
            case FORWARD -> 1*orientations.get(object).toUnitVector().getX();
            case BACKWARD -> -1*orientations.get(object).toUnitVector().getX();
            case LEFT,RIGHT -> 0;
        };
        int currentPosition = -1;
        for (Map.Entry<Integer, String> entry : worldmap.entrySet()) {
            if (entry.getValue().equals(object)) {
                currentPosition = entry.getKey();
                break;
            }
        }
        if( currentPosition!=-1&& move!=0 && canMoveTo(currentPosition+move)){
            String word2= objectAt(currentPosition+move);
            MapDirection orientation2=orientations.get(word2);
            worldmap.remove(currentPosition);
            worldmap.remove(currentPosition+move);
            orientations.remove(object);
            orientations.remove(word2);
            worldmap.put(currentPosition,word2);
            worldmap.put(currentPosition+move,object);
            orientations.put(word2,orientation);
            orientations.put(object,orientation2);
        }

    }

    @Override
    public boolean isOccupied(Integer position) {
        return false;
    }

    @Override
    public String objectAt(Integer position) {
        return worldmap.get(position);
    }

    @Override
    public String toString() {
        String text = "[";
        text+=worldmap.get(0);
        for(int i=1;i<worldmap.size();i++){
            text+=",";
            text+=worldmap.get(i);

        }
        text+="]";
        return text;

    }
}
