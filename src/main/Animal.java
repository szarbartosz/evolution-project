import java.util.*;

public class Animal implements IMapElement {

    private Vector2D position;
    private Direction orientation;
    private double energy;
    private Integer[] genotype;
    WorldMap map;
    Set<IPositionChangeObserver> observersSet = new HashSet<>();

    public Animal (WorldMap map){
        this(new Vector2D(2,2), map);
    }

    public Animal (Vector2D initialPosition, WorldMap map){
        this(initialPosition, Animal.randomGenotype(), map);
    }

    public Animal(Vector2D initialPosition, Integer[] genotype, WorldMap map){
        this.position = initialPosition;
        this.orientation = Direction.N;
        this.energy = map.startEnergy;
        this.genotype = genotype;
        this.map = map;
    }

    private static Integer[] randomGenotype(){
        Random r = new Random();
        Integer[] genotype = new Integer[32];
        for (int i = 0; i < 32; i++){
            genotype[i] = r.nextInt(8);
        }
        return genotype;
    }

    @Override
    public String toString(){
        switch(this.orientation){
            case N: return "↑";
            case NE: return "↗";
            case E: return "→";
            case SE: return "↘";
            case S: return "↓";
            case SW: return "↙";
            case W:  return "←";
            case NW: return "↖";
            default: return null;
        }
    }

    public void turn(int number){
        for (int i = 0; i < number; i++){
            this.orientation = this.orientation.next();
        }
    }

    public void autoTurn(){
        int rnd = new Random().nextInt(this.genotype.length);
        this.turn(this.genotype[rnd]);
    }

    public void move(){
        Vector2D newVector = map.betterPosition(this.position.add(this.orientation.toUnitVector()));
        if (map.canMoveTo(newVector)){
            positionChanged(this.getPosition(), newVector);
            this.position = newVector;
            this.energy -= map.moveEnergy;
        }
    }

    protected void addObserver(IPositionChangeObserver observer){
        observersSet.add(observer);
    }

    protected void removeObserver(IPositionChangeObserver observer){
        observersSet.remove(observer);
    }

    public Vector2D getPosition(){
        return this.position;
    }

    public Direction getOrientation(){
        return this.orientation;
    }

    private void positionChanged(Vector2D oldPosition, Vector2D newPosition) {
        for (IPositionChangeObserver observer : observersSet) {
            observer.positionChanged(oldPosition, newPosition, this);
        }
    }

    public boolean isDead(){
        return (energy <= 0);
    }

    public double getEnergy() {
        return energy;
    }

    public void eat(double addEnergy){
        this.energy += addEnergy;
    }
}