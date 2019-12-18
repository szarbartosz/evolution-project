package projectStructure;

import features.Vector2D;

public class Grass implements IMapElement {

    private Vector2D position;

    public Grass(Vector2D position){
        this.position = position;
    }

    public Vector2D getPosition() {
        return position;
    }

    @Override
    public String toString(){
        return "*";
    }
}
