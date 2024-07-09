package agh.ics.oop.model;

public class Grass implements WorldElement {
    private final Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }
    @Override
    public Vector2d getPosition() {
        return position;
    }
    @Override
    public String getTexture(){
        return"grass.png";
    }
    @Override
    public String getTextureText(){
        return "Trawa";
    }
    @Override
    public String toString() {
        return "*";
    }
}
