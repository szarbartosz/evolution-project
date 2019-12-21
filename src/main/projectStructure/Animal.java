package projectStructure;

import features.Direction;
import features.Vector2D;

import java.util.*;

public class Animal implements IMapElement {

    private Vector2D position;
    private Direction orientation;
    private double energy;
    public final Integer[] genotype;
    WorldMap map;
    Set<IPositionChangeObserver> observersSet = new HashSet<>();

    public Animal (WorldMap map){
        this(new Vector2D(2,2), map);
    }

    public Animal (Vector2D initialPosition, WorldMap map){
        this(initialPosition, Animal.randomGenotype(), map);
    }

    public Animal(Vector2D initialPosition, Integer[] genotype, WorldMap map){
        this(initialPosition, genotype, map, map.startEnergy);
    }

    public Animal(Vector2D initialPosition, Integer[] genotype, WorldMap map, double startEnergy){
        this.position = map.betterPosition(initialPosition);
        this.orientation = Direction.N;
        this.energy = map.startEnergy;
        this.genotype = genotype;
        this.map = map;
        int rand = new Random().nextInt(8);
        this.turn(rand);
        this.energy = startEnergy;
    }

    private static Integer[] randomGenotype(){
        Random rand = new Random();
        Integer[] genotype = new Integer[32];
        for (int i = 0; i < 32; i++){
            genotype[i] = rand.nextInt(8);
        }

        int[] eliminatedGenes = new int[8];
        Arrays.fill(eliminatedGenes, 0);
        for (Integer gene : genotype){
            eliminatedGenes[gene]++;
        }

        int repeat = 0;
        for (int i = 0; i < 8; i++){
            if (eliminatedGenes[i] == 0){
                while(repeat < 32){
                    int randomGene = rand.nextInt(32);
                    if (eliminatedGenes[genotype[randomGene]] != 1){
                        genotype[randomGene] = i;
                        break;
                    }
                    repeat++;
                }
            }
        }

        Arrays.sort(genotype);

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
        int rand = new Random().nextInt(this.genotype.length);
        this.turn(this.genotype[rand]);
    }

    public void move(){
        Vector2D vec = map.betterPosition(this.position.add(this.orientation.toUnitVector()));
        if (map.canMoveTo(vec)){
            positionChanged(this.getPosition(), vec);
            this.position = vec;
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

    public double getEnergy() {
        return energy;
    }

    public Integer[] getGenotype(){
        return this.genotype;
    }

    private void positionChanged(Vector2D oldPosition, Vector2D newPosition) {
        for (IPositionChangeObserver observer : observersSet) {
            observer.positionChanged(oldPosition, newPosition, this);
        }
    }

    public boolean isDead(){
        return (energy <= 0);
    }

    public void eat(double addEnergy){
        this.energy += addEnergy;
    }

    public boolean canProcreate(){
        return this.energy > (map.startEnergy / 2);
    }

    public Animal procreate(Animal other){
        Random random = new Random();
        List <Vector2D> emptyPositions = new ArrayList<>();
        for (Direction azimuth : Direction.values()){
            Vector2D newPosition = this.map.betterPosition(this.position.add(azimuth.toUnitVector()));
            if (this.map.objectAt(newPosition) == null){
                emptyPositions.add(newPosition);
            }
        }

        if (emptyPositions.isEmpty()) return null;

        Vector2D childPosition = emptyPositions.get(random.nextInt(emptyPositions.size()));

        double childEnergy = this.energy / 4 + other.energy / 4;

        Integer[] childGenotype = new Integer[32];
        for (int i = 0; i < 11; i++){
            childGenotype[i] = this.genotype[i];
        }

        for (int i = 11; i < 21; i++){
            childGenotype[i] = other.genotype[i];
        }

        for (int i = 21; i < 32; i++){
            childGenotype[i] = this.genotype[i];
        }
        int[] eliminatedGenes = new int[8];
        Arrays.fill(eliminatedGenes, 0);
        for (Integer gen : childGenotype){
            eliminatedGenes[gen]++;
        }
        int repeat = 0;
        for (int i = 0; i < 8; i++){
            if (eliminatedGenes[i] == 0){
                while(repeat < 32){
                    int randomGene = random.nextInt(32);
                    if (eliminatedGenes[childGenotype[randomGene]] != 1){
                        childGenotype[randomGene] = i;
                        break;
                    }
                    repeat++;
                }

            }
        }

        Arrays.sort(childGenotype);

        Animal child = new Animal(childPosition, childGenotype, this.map, childEnergy);
        return child;
    }
}