import features.Vector2D;
import org.junit.Assert;
import org.junit.Test;
import projectStructure.Animal;
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
    public void autoTurnTest(){
        WorldMap map = new WorldMap(new Vector2D(8,8));
        Integer[] genotype = new Integer[]{0,1,3,5,1};
        Animal fox = new Animal(new Vector2D(6,6), genotype, map);
        System.out.println(fox);
        fox.autoTurn();
        System.out.println(fox);
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
}