import org.junit.Assert;
import org.junit.Test;
import projectStructure.Animal;
import features.Direction;
import features.Vector2D;
import projectStructure.WorldMap;

import java.util.Arrays;

public class AnimalTest {
    WorldMap map = new WorldMap(new Vector2D(100,100));
    Integer[] parrotGenotype = new Integer[]{0,0,0,0,0,0,0,0,0,0,1,1,2,2,2,3,3,3,4,4,4,4,4,5,5,6,6,6,6,7,7,7};
    private Animal duck = new Animal(map);
    private Animal ant = new Animal(map);
    private Animal snail = new Animal(new Vector2D(10,10), map);
    private Animal parrot = new Animal(new Vector2D(25,25), parrotGenotype, map);

    @Test
    public void constructorTest(){
        Assert.assertEquals(duck.getPosition(),new Vector2D(2,2));
        Assert.assertEquals(ant.getPosition(),new Vector2D(2,2));
        Assert.assertEquals(snail.getPosition(),new Vector2D(10,10));
        Assert.assertEquals(parrot.getPosition(),new Vector2D(25,25));
    }

    @Test
    public void turnTest(){
        duck.turn(2);
        ant.turn(3);
        snail.turn(5);
        parrot.turn(6);
        Assert.assertEquals(duck.getOrientation(), Direction.E);
        Assert.assertEquals(ant.getOrientation(), Direction.SE);
        Assert.assertEquals(snail.getOrientation(), Direction.SW);
        Assert.assertEquals(parrot.getOrientation(), Direction.W);
    }

    @Test
    public void moveTest(){
        duck.turn(2);
        ant.turn(3);
        snail.turn(5);
        parrot.turn(6);
        duck.move();
        ant.move();
        snail.move();
        parrot.move();
        Assert.assertEquals(duck.getPosition(),new Vector2D(3,2));
        Assert.assertEquals(ant.getPosition(),new Vector2D(3,1));
        Assert.assertEquals(snail.getPosition(),new Vector2D(9,9));
        Assert.assertEquals(parrot.getPosition(),new Vector2D(24,25));
    }

    @Test
    public void procreationTest() {
        for (int i = 0; i < 100; i++) {
            WorldMap map = new WorldMap(new Vector2D(2, 2));
            Animal mrFox = new Animal(new Vector2D(1, 1), map);
            Animal mrsFox = new Animal(new Vector2D(1, 1), map);
            Animal foxJunior = mrFox.procreate(mrsFox);

            boolean[] eliminatedGenes = new boolean[8];
            Arrays.fill(eliminatedGenes, false);
            for (Integer gene : foxJunior.genotype) {
                eliminatedGenes[gene] = true;
            }

            for (int j = 0; j < 8; j++) {
                System.out.println(Arrays.toString(foxJunior.genotype));
                Assert.assertTrue(eliminatedGenes[j]);
            }
        }
    }

    @Test
    public void energyAndDeadTest(){
        WorldMap map = new WorldMap(new Vector2D(6,6),10.0);
        Animal snail = new Animal(new Vector2D(5,5), map);
        Animal frog = new Animal(new Vector2D(6,0), map);
        map.place(frog);
        map.place(snail);
        Assert.assertEquals(10.0, snail.getEnergy(), 0.01);
        snail.move();
        Assert.assertEquals(8.0, snail.getEnergy(), 0.01);
        snail.move();
        Assert.assertEquals(6.0, snail.getEnergy(), 0.01);
        snail.move();
        Assert.assertEquals(4.0, snail.getEnergy(), 0.01);
        snail.move();
        Assert.assertEquals(2.0, snail.getEnergy(), 0.01);
        snail.move();
        Assert.assertEquals(0.0, snail.getEnergy(), 0.01);
        Assert.assertEquals(snail, map.objectAt(new Vector2D(5,3)));
        map.clearDeadAnimals();
        Assert.assertNotEquals(snail, map.objectAt(new Vector2D(5,3)));
        Assert.assertEquals(frog, map.objectAt(new Vector2D(6,0)));
        frog.move();
        frog.move();
        frog.move();
        frog.move();
        frog.move();
        Animal turtle = new Animal(new Vector2D(6,5), map);
        map.place(turtle);
        map.clearDeadAnimals();
        Assert.assertEquals(turtle, map.objectAt(new Vector2D(6,5)));
    }
}