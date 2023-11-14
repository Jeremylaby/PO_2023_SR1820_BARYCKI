package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal(Vector2d position) {
        this.orientation = MapDirection.NORTH;
        this.position=position;
    }

    public Animal() {this(new Vector2d(2,2));}

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }



    @Override
    public String toString() {
        return "Animal{" +
                "orientation=" + orientation +
                ", position=" + position +
                '}';
    }
    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }
    public void move(MoveDirection direction){
        int x=position.getX();
        int y=position.getY();
        int nx=orientation.toUnitVector().getX();
        int ny=orientation.toUnitVector().getY();
        switch (direction){
            case FORWARD :
                if(x+nx<=4 && x+nx>=0&&y+ny<=4 && y+ny>=0) {
                    position=position.add(orientation.toUnitVector());
                }
                break;
            case BACKWARD:
                if(x-nx<=4 && x-nx>=0&&y-ny<=4 && y-ny>=0){
                    position=position.subtract(orientation.toUnitVector());
                }
                break;
            case RIGHT:
                orientation=orientation.next();
                break;
            case LEFT :
                orientation=orientation.previous();
                break;
        }
    }
}
